package main.health.com.s_health.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import main.health.com.s_health.Adapters.MedicamentAdapter;
import main.health.com.s_health.Entities.Medicament;
import main.health.com.s_health.Fragments.Scan;
import main.health.com.s_health.R;
import main.health.com.s_health.Utils.AppController;

public class MainActivity extends AppCompatActivity {
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(GoogleMaterial.Icon.gmd_wb_sunny);
    SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(GoogleMaterial.Icon.gmd_access_alarm);;



    ListView listTravelsList;
    public static List<Medicament> medicaments=new ArrayList<>();
    MedicamentAdapter medicamentAdapter;
    public static String urlCat="http://societymedicines-soietyhealth.rhcloud.com/societyMedicines/feeds/ShowAllMedicines";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        Log.d("aaaa", "===================================================");
        for (Medicament m :
                medicaments) {
            System.out.println(m.toString());

        }
        Log.d("aaaa", "===================================================");


        FragmentManager fg = getFragmentManager();
        FragmentTransaction ft = fg.beginTransaction().add(R.id.container, new Scan());
        ft.commit();
        final IProfile profile = new ProfileDrawerItem().withName("Elyes Ben Salah").withEmail("elyes.bensalah@esprit.tn").withIcon(Uri.parse("https://lh3.googleusercontent.com/-K3UDDeTXZXY/UmBwaARfuZI/AAAAAAAAACA/sB7r2MFHvw4/w139-h140-p/%25E8%25B1%25AA%25E9%25AC%25BC%252C%2BG%25C5%258Dki.jpeg")).withIdentifier(100);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.dr_bg)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        new DrawerBuilder().withActivity(this)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("Fuck the World")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .build();
    }


    void loadData(){
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(urlCat,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        // if faut changer le name & email par title et dateEvent ,jsonObject.getString("11/10/1990")

                        Medicament t = new Medicament(jsonObject.getInt("idMedicine"),jsonObject.getString("label"),jsonObject.getInt("quantity"),jsonObject.getBoolean("isAvailable"),jsonObject.getInt("barCode"),jsonObject.getString("expiryDate") ,jsonObject.getString("imageURl"));
                        Log.d("MEDIC",t.toString());
                        medicaments.add(t);
                    }
                    //MedicamentAdapter adapter = new MedicamentAdapter(this,R.layout.travel_row,travels);
                    //listTravelsList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });
        // Remove the url from cache
        AppController.getInstance().getRequestQueue().getCache().remove(urlCat);
        // Disable the cache for this url, so that it always fetches updated
        // json
        jsonObjReq.setShouldCache(false);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    void parseJsonProduit (List<Medicament> list,String json){
        try {
            JSONArray array=new JSONArray(json);

            for(int i = 0 ;i<array.length();i++){
                JSONObject j=array.getJSONObject(i);
                Medicament p = new Medicament();
                p.setId(j.optInt("idMedicine"));
                p.setLabel(j.optString("label"));
                p.setQuantity(j.optInt("quantity"));
                p.setIsAvailable(j.optBoolean("isAvailable"));
                p.setBarCode(j.optInt("barCode"));
                p.setUrlImg(j.optString("imageURl"));
                list.add(p);
            }
        } catch (JSONException e) {
            Log.d("JSON ",e.getMessage());
        }
        Log.d("parseJsonProduit ", "Done");
    }

    class AsycGetProducts extends AsyncTask<String, String, String> {
        ProgressDialog barProgressDialog = new ProgressDialog(getActivity());

        public List<Produit> getList(){
            return produits;
        }
        @Override
        protected void onPreExecute() {
            barProgressDialog.setTitle("Loading...");
            barProgressDialog.setMessage("Please Wait...");
            barProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            barProgressDialog.show();
            Log.d("onPreExecute ","Done");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String jsonProduit=HelperHttp.getJSONResponseFromURL(urlCat);
            parseJsonProduit(medicaments, jsonProduit);

            Log.v("product",medicaments+"");

            Log.d("doInBackGroud ","Done");
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("onPostExecute ","start");




            barProgressDialog.dismiss();
            Log.d("onPostExecute ","Done");
            super.onPostExecute(result);
        }

    }
}
