package com.flibustier.android.recipe;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flibustier.android.recipe.Helper.DBHelper;
import com.flibustier.android.recipe.Helper.ImageHelper;
import com.flibustier.android.recipe.MainRecipeAdapter;
import com.flibustier.android.recipe.Model.RecipeItem;
import com.flibustier.android.recipe.R;
import com.flibustier.android.recipe.RecipeActivity;
import com.flibustier.android.recipe.SearchFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {
    ImageHelper imageHelper = new ImageHelper();
    ArrayList<RecipeItem> bestList;
    ArrayList<RecipeItem> newList;
    ArrayList<RecipeItem> fullList;

    Date today = new Date();

    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check search recipes menu on the slide menu
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_search);
                //connect to search recipes page
                FragmentManager manager = getActivity().getSupportFragmentManager();
                SearchFragment searchFragment = new SearchFragment();
                manager.beginTransaction().replace(R.id.root_layout, searchFragment, searchFragment.getTag()).addToBackStack(null).commit();
            }
        });

        GridView newGridView = (GridView) view.findViewById(R.id.GridView_New);

        //Connect DB
        DBHelper dbHelper = new DBHelper(getContext(), "Recipes.db", null, 1);


        ArrayList<RecipeItem> defaultDataList = dbHelper.recipes_SelectAll();
        if (defaultDataList == null || defaultDataList.size() == 0) {

            //Set shahipaneer data
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.shahipaneer, getActivity().getTheme());
            byte[] shahipaneer = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "shahi paneer", "Shubhnit", today.toString(),
                    "1. Heat the oil in a large skillet over medium heat. Cook the onion and garlic in the hot oil until the onions are soft and golden brown, about 5 minutes. Sprinkle the cumin, coriander, turmeric, and chili powder over the onion and garlic; continue cooking until the seasonings are fragrant, about 30 seconds." +
                            " \n 2. Pour the pureed tomatoes into the skillet; cook until the excess liquid evaporates and the oil separates, 3 to 5 minutes. Add the paneer, water, sugar, and salt to the mixture; stir gently so the paneer does not break apart. Cook until the paneer begins to absorb some of the liquid, about 10 minutes. Stir the cream into the mixture and simmer another 5 minutes. Garnish with cilantro to serve." +
                            "\n 3. mix well", "indian traditional food",
                    shahipaneer, shahipaneer, 20);

            int shahipaneerId = dbHelper.recipes_GetIdByName("shahi paneer");
            ArrayList<String> shahipaneerIngre = new ArrayList<>();
            shahipaneerIngre.add("cooking oil");
            shahipaneerIngre.add("onion");
            shahipaneerIngre.add("garlic");
            shahipaneerIngre.add("tomato");
            shahipaneerIngre.add("red chilli");
            shahipaneerIngre.add("paneer");
            shahipaneerIngre.add("cream");
            shahipaneerIngre.add("salt");


            for (int i = 0; i < shahipaneerIngre.size(); i++) {
                dbHelper.ingredients_Insert(shahipaneerId, shahipaneerIngre.get(i));
            }

            //Set chickenbiryani data
            drawable = getResources().getDrawable(R.drawable.chickenbiryani, getActivity().getTheme());
            byte[] chickenbiryani = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Chicken Biryani", "Shubhnit", today.toString(),
                    "1.Combine chicken breasts and thighs, yogurt, paprika, and 1/2 teaspoon each of turmeric,cumin, and salt in a shallow dish or plastic bag to marinate. Chill while rice is prepared.\n" +
                            "2.Preheat oven to 375°F. Bring a medium saucepan of water to a boil over high. Stir in rice,cardamom, 2 teaspoons of the garam masala, and 1/2 teaspoon of the salt. Boil 6 minutes; drain,and discard cardamom pods. Set aside. \n" +
                            "3.Heat oil in a shallow Dutch oven over medium. Add onion, and cook, stirring occasionally,until softened and translucent, 5 to 7 minutes. Stir in ginger, garlic, remaining 1/2 teaspoon salt,1/2 teaspoon turmeric, 1 teaspoon cumin, and 2 teaspoons garam masala; cook, stirring oftenuntil fragrant, about 1 minute. Stir in serrano chile. Add tomatoes; cook stirring occasionally,until tomatoes have softened and released their juices, 3 to 4 minutes.\n" +
                            "4.Increase heat to medium-high, and add chicken mixture; cook, stirring often for 3 minutes.Remove from heat. Stir together warm milk and saffron in a small bowl; let stand 2 minutes. \n" +
                            "5. Sprinkle half of the cilantro and mint over tomato-chicken mixture; top evenly with rice, anddrizzle with saffron milk. Cover, and bake in preheated oven until rice and chicken are cookedthrough, about 15 minutes. Remove lid, and sprinkle with remaining half of cilantro and mint.Top with Fresno chile, if desired.  \n",
                    "staple indian food",
                    chickenbiryani, chickenbiryani, 48);

            int chickenbiryaniId = dbHelper.recipes_GetIdByName("Chicken Biryani");
            ArrayList<String> chickenbiryaniIngre = new ArrayList<>();
            chickenbiryaniIngre.add("chicken");
            chickenbiryaniIngre.add("yogurt");
            chickenbiryaniIngre.add("turmeric");
            chickenbiryaniIngre.add("cumin");
            chickenbiryaniIngre.add("rice");
            chickenbiryaniIngre.add("cardamom");
            chickenbiryaniIngre.add("garam masala");
            chickenbiryaniIngre.add("oil");
            chickenbiryaniIngre.add("onion");
            chickenbiryaniIngre.add("ginger");
            chickenbiryaniIngre.add("cloves");
            chickenbiryaniIngre.add("tomato");
            chickenbiryaniIngre.add("milk");


            for (int i = 0; i < chickenbiryaniIngre.size(); i++) {
                dbHelper.ingredients_Insert(chickenbiryaniId, chickenbiryaniIngre.get(i));
            }

            //Set pancake data
            drawable = getResources().getDrawable(R.drawable.pancake, getActivity().getTheme());
            byte[] pancake = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("America", "Pancakes", "Shubhnit", today.toString(),
                    "1.In a medium bowl, whisk together the flour, sugar, baking powder, and salt. Add the milk, egg, and 1 tablespoon melted butter and whisk just to combine.\n" +
                            "2.Working in batches, melt 1/2 tablespoon of the remaining butter in a large non-stick skillet over medium heat. For each pancake, spoon 2 tablespoons of batter into the skillet and cook until bubbles appear on the tops and the bottoms are golden, 1 to 2 minutes. \n" +
                            "3.Flip the pancakes and cook 30 seconds more. Repeat with remaining butter and batter. \n",
                    "staple american breakfast food",
                    pancake, pancake, 14);

            int pancakeId = dbHelper.recipes_GetIdByName("Pancakes");
            ArrayList<String> pancakeIngre = new ArrayList<>();
            pancakeIngre.add("all purpose flour");
            pancakeIngre.add("sugar");
            pancakeIngre.add("baking powder");
            pancakeIngre.add("milk");
            pancakeIngre.add("egg");
            pancakeIngre.add("butter");



            for (int i = 0; i < pancakeIngre.size(); i++) {
                dbHelper.ingredients_Insert(pancakeId, pancakeIngre.get(i));
            }



            //Set kadhaichicken data
            drawable = getResources().getDrawable(R.drawable.kadhaichicken, getActivity().getTheme());
            byte[] kadhaichicken = imageHelper.getByteArrayFromDrawable(drawable);


            dbHelper.recipes_Insert("india", "kadhai chicken", "Shubhnit", today.toString(),
                    "1.Take a non-stick pan and roast the coriander seeds, once the seeds start crackling, remove and crush them thoroughly and keep it aside..\n" +
                            "2.  Heat oil in a pan over a low flame, add red chillies and fenugreek seeds. Stir for a few seconds. Add the onions and increase the flame to medium. Cook till onions turn light brown. Add garlic and stir.\n" +
                            "3. Now add ginger paste, coriander seeds, red chilli powder and coriander powder. Add chicken pieces and cook for 3-4 minutes on high flame. Stir to blend all the masala with the chicken.\n" +
                            "4. Now add the chopped tomatoes and cook for 3 minutes. Add salt, dry mango powder and garam masala powder.\n" +
                            "5. Cover the pan with a lid and cook for 10 minutes till chicken is tender. Stir from time to time and add the tomato puree. Then add the coriander leaves and cook for a minute. Now add the capsicum, tomato, ginger slices and green chillies. Mix well and reduce the flame.\n" +
                            "6. Add milk and mix. Add water to make it semi-dry and cook for a minute. Remove from heat and serve hot with rice or rotis.", "indian traditional food",
                    kadhaichicken, kadhaichicken, 4);

            int kadhaichickenId = dbHelper.recipes_GetIdByName("kadhai chicken");
            ArrayList<String> kadhaichickenIngre = new ArrayList<>();
            kadhaichickenIngre.add("chicken");
            kadhaichickenIngre.add("coriander");
            kadhaichickenIngre.add("vegetable oil");
            kadhaichickenIngre.add("onion");
            kadhaichickenIngre.add("red chilli");
            kadhaichickenIngre.add("tomato");
            kadhaichickenIngre.add("milk");
            kadhaichickenIngre.add("garlic");


            for (int i = 0; i < kadhaichickenIngre.size(); i++) {
                dbHelper.ingredients_Insert(kadhaichickenId, kadhaichickenIngre.get(i));
            }
            //Set rajmah data
            drawable = getResources().getDrawable(R.drawable.rajmah, getActivity().getTheme());
            byte[] rajmah = imageHelper.getByteArrayFromDrawable(drawable);


            dbHelper.recipes_Insert("india", "Rajmah", "Shubhnit", today.toString(),
                    "1.Place the kidney beans into a large container and cover with several inches of cool water; let stand 8 hours or overnight. Drain and rinse.Grind the onion, ginger, and garlic into a paste using a mortar and pestle.\n" +
                            "2.  Heat the oil and ghee together in a pressure cooker over medium heat. Fry the red chile peppers, cumin seeds, and whole cloves in the hot oil until the cumin seeds begin to splutter; stir the onion paste into the mixture and cook, stirring frequently, until golden brown.\n" +
                            "3. Season with the ground turmeric, ground cumin, and ground coriander; continue cooking for a few more seconds before adding the tomatoes. Cook until the tomatoes are completely tender.\n" +
                            "4. Add the drained kidney beans to the pressure cooker with enough water to cover; pour the 2 cups water additionally to the cooker.\n" +
                            "5.  Add the sugar and salt. Close the pressure cooker and bring to 15 pounds of pressure; cook about 40 minutes. Lower the heat to low and cook another 10 to 15 minutes. \n" +
                            "6. Release the pressure and open the cooker. Stir the garam masala and ground red pepper into the bean mixture; garnish with chopped cilantro to serve.", "indian traditional food",
                    rajmah, rajmah, 17);

            int rajmahId = dbHelper.recipes_GetIdByName("Rajmah");
            ArrayList<String> rajmahIngre = new ArrayList<>();
            rajmahIngre.add("kidney beans");
            rajmahIngre.add("onion");
            rajmahIngre.add("garlic");
            rajmahIngre.add("ginger");
            rajmahIngre.add("oil");
            rajmahIngre.add("ghee");
            rajmahIngre.add("red chilli");
            rajmahIngre.add("cumin");
            rajmahIngre.add("cloves");
            rajmahIngre.add("turmeric");
            rajmahIngre.add("coriander");
            rajmahIngre.add("tomato");
            rajmahIngre.add("garam masala");


            for (int i = 0; i < rajmahIngre.size(); i++) {
                dbHelper.ingredients_Insert(rajmahId, rajmahIngre.get(i));
            }

            //Set aloogobhi data
            drawable = getResources().getDrawable(R.drawable.aloogobhi, getActivity().getTheme());
            byte[] aloogobhi = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Aloo Gobhi", "Shubhnit", today.toString(),
                    "1.firstly, in a large kadai, heat 3 tsp oil and saute 1 tsp cumin and 1 tsp kasuri methi until they turn aromatic.further, add 1 onion and saute well.now add 1 tsp ginger garlic paste, 1 chilli and saute for a minute.\n" +
                            "2.keeping the flame on low add ¼ tsp turmeric, ½ tsp chilli powder, ½ tsp coriander powder, ¼ tsp cumin powder and ¾ tsp salt.saute on low flame, until the spices turn aromatic.further, add 1 tomato and saute until tomatoes turn soft and mushy. \n" +
                            "3.now add fried aloo and gobi.mix gently without breaking them. if you prefer gravy, then add ½ cup water.furthermore, add ½ tsp aamchur, ¼ tsp garam masala and 2 tbsp coriander leaves. mix well.finally, aloo gobi dry recipe is ready to serve with roti or rice. \n",
                    "staple indianan  food",
                    aloogobhi, aloogobhi, 22);

            int aloogobhiId = dbHelper.recipes_GetIdByName("Aloo Gobhi");
            ArrayList<String> aloogobhiIngre = new ArrayList<>();
            aloogobhiIngre.add("oil");
            aloogobhiIngre.add("cauliflower");
            aloogobhiIngre.add("potato");
            aloogobhiIngre.add("cumin");
            aloogobhiIngre.add("fenugreek");
            aloogobhiIngre.add("onion");
            aloogobhiIngre.add("ginger");
            aloogobhiIngre.add("garlic");
            aloogobhiIngre.add("red chilli");
            aloogobhiIngre.add("coriander");
            aloogobhiIngre.add("tomato");
            aloogobhiIngre.add("dry mango powder");
            aloogobhiIngre.add("garam masala");


            for (int i = 0; i < aloogobhiIngre.size(); i++) {
                dbHelper.ingredients_Insert(aloogobhiId, aloogobhiIngre.get(i));
            }

            //Set malaikofta data
            drawable = getResources().getDrawable(R.drawable.malaikofta, getActivity().getTheme());
            byte[] malaikofta = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Malai Kofta", "Shubhnit", today.toString(),
                    "1.firstly, in a large mixing bowl take 3 potato and ¾ cup paneer.also add 1 chilli, 2 tbsp coriander, ¼ tsp cumin powder and ½ tsp salt.add 2 tbsp raisins and 2 tbsp cashew to have crunchy bite in kofta.mix well making sure all the spices are well combined\n" +
                            "2.now add 2 tbsp maida and mix well forming a soft dough. maida helps to absorb moisture and bind the mixture well.prepare a small ball sized kofta by greasing hand with oil.deep fry on medium hot oil.\n" +
                            "3.firstly, in a large kadai, heat 3 tsp oil and saute 1 tsp cumin and 1 tsp kasuri methi until they turn aromatic.further, add 1 onion and saute well.now add 1 tsp ginger garlic paste, 1 chilli and saute for a minute.\n" +
                            "4.stir occasionally, making sure the koftas are cooked uniformly.fry until the kofta turn golden brown and crisp.drain off the koftas and keep aside. \n" +
                            "5.firstly, in a pan heat 2 tbsp oil and saute 1 onion, 1 tsp ginger garlic paste.saute until onions changes colour slightly.further add 2 tomato and saute slightly.now add 2 tbsp cashew and continue to saute until tomatoes soften completely.\n" +
                            "6.cool completely and transfer to a blender.blend to smooth paste adding water if required.now filter the mixture to get rid of skin and seeds. \n" +
                            "7.in a large kadai heat 1 tbsp butter and 2 tbsp oil.saute 1 tsp cumin, 2 pod cardamom, 1 bay leaf, 1 inch cinnamon, 2 clove until it turns aromatic.further keeping the flame on low, add 1 tsp chilli powder, ½ tsp turmeric, ¾ tsp coriander powder and ¼ tsp cumin powder.saute until the spices turn aromatic. \n" +
                            "8.further add in the prepared onion tomato puree, 1 tsp salt and mix well.cover and cook until the mixture starts to thicken and oil separates from sides.now add ¼ cup cream and mix on low flame until it's well combined. \n" +
                            "9.further, add ½ cup water and mix well adjusting consistency as required.get the curry to a boil, add 1 tsp kasuri methi and ¼ tsp garam masala. mix well.finally, pour the curry over kofta and malai kofta is ready to enjoy. \n",
                    "staple indian main course food",
                    malaikofta, malaikofta, 18);

            int malaikoftaId = dbHelper.recipes_GetIdByName("Malai Kofta");
            ArrayList<String> malaikoftaIngre = new ArrayList<>();
            malaikoftaIngre.add("potato");
            malaikoftaIngre.add("paneer");
            malaikoftaIngre.add("coriander");
            malaikoftaIngre.add("cumin");
            malaikoftaIngre.add("raisins");
            malaikoftaIngre.add("cashew");
            malaikoftaIngre.add("maida");
            malaikoftaIngre.add("oil");
            malaikoftaIngre.add("red chilli");
            malaikoftaIngre.add("ginger");
            malaikoftaIngre.add("garlic");
            malaikoftaIngre.add("tomato");
            malaikoftaIngre.add("garam masala");
            malaikoftaIngre.add("butter");
            malaikoftaIngre.add("bay leaf");
            malaikoftaIngre.add("clove");
            malaikoftaIngre.add("cinnamon");
            malaikoftaIngre.add("cream");
            malaikoftaIngre.add("fenugreek");
            malaikoftaIngre.add("garam masala");


            for (int i = 0; i < malaikoftaIngre.size(); i++) {
                dbHelper.ingredients_Insert(malaikoftaId, malaikoftaIngre.get(i));
            }

            //Set chanamasala data
            drawable = getResources().getDrawable(R.drawable.chanamasala, getActivity().getTheme());
            byte[] chanamasala = imageHelper.getByteArrayFromDrawable(drawable);


            dbHelper.recipes_Insert("india", "Chana Masala", "Shubhnit", today.toString(),
                    "1.Grind onion, tomato, ginger, garlic, and chile pepper together in a food processor into a paste.\n" +
                            "2.Heat olive oil in a large skillet over medium heat. Fry bay leaves in hot oil until fragrant, about 30 seconds. Pour the paste into the skillet and cook until the oil begins to separate from the mixture and is golden brown in color, 2 to 3 minutes. Season the mixture with chili powder, coriander, powder, gram masala, turmeric, and salt; cook and stir until very hot, 2 to 3 minutes. \n" +
                            "3.Stir enough water into the mixture to get a thick gravy; bring to a boil and stir chickpeas into the gravy. Reduce heat to medium and cook until the chickpeas are heated through, 5 to 7 minutes. Garnish with cilantro.", "indian traditional food",
                    chanamasala, chanamasala, 17);

            int chanamasalaId = dbHelper.recipes_GetIdByName("Chana Masala");
            ArrayList<String> chanamasalaIngre = new ArrayList<>();
            chanamasalaIngre.add("onion");
            chanamasalaIngre.add("green chilli");
            chanamasalaIngre.add("curry powder");
            chanamasalaIngre.add("oil");
            chanamasalaIngre.add("bay leaves");
            chanamasalaIngre.add("coriander");
            chanamasalaIngre.add("garam masala");
            chanamasalaIngre.add("turmeric powder");
            chanamasalaIngre.add("garlic");
            chanamasalaIngre.add("tomato");
            chanamasalaIngre.add("ginger");
            chanamasalaIngre.add("chickpea");


            for (int i = 0; i < chanamasalaIngre.size(); i++) {
                dbHelper.ingredients_Insert(chanamasalaId, chanamasalaIngre.get(i));
            }

            //Set butterchicken data
            drawable = getResources().getDrawable(R.drawable.butterchicken, getActivity().getTheme());
            byte[] butterchicken = imageHelper.getByteArrayFromDrawable(drawable);


            dbHelper.recipes_Insert("india", "Butter Chicken", "Shubhnit", today.toString(),
                    "1.Mix garam masala, tandoori masala, curry, cumin, cardamom, cayenne, salt, and black pepper together in a small bowl to make spice mixture.\n" +
                            "2.  Place chicken in a large bowl and add 1/2 the spice mixture; turn to coat evenly.\n" +
                            "3. Melt 1 tablespoon butter in a large skillet over medium heat. Add chicken; cook and stir until lightly browned, about 10 minutes. Remove from heat.\n" +
                            "4. Melt remaining 2 tablespoons butter in a large saucepan over medium heat. Add onion; cook and stir until soft and translucent, about 5 minutes. Stir in remainder of the spice mixture, lemon juice, garlic, and ginger; cook and stir until combined, about 1 minute.\n" +
                            "5.  Stir tomato puree into onion mixture and cook, stirring frequently, about 2 minutes. Pour in half-and-half and yogurt. Reduce heat to low and simmer sauce, stirring frequently, about 10 minutes. Remove from heat. \n" +
                            "6. Blend cashews in a blender until finely ground. Add sauce to the blender; puree until smooth.Pour blended sauce over chicken in the skillet. Simmer until thickened, 10 to 15 minutes. Garnish with cilantro.", "indian traditional food",
                    butterchicken, butterchicken, 23);

            int butterchickenId = dbHelper.recipes_GetIdByName("Butter Chicken");
            ArrayList<String> butterchickenIngre = new ArrayList<>();
            butterchickenIngre.add("garam masala");
            butterchickenIngre.add("tandoori masala");
            butterchickenIngre.add("curry powder");
            butterchickenIngre.add("cumin");
            butterchickenIngre.add("cardamom");
            butterchickenIngre.add("pepper");
            butterchickenIngre.add("chicken");
            butterchickenIngre.add("butter");
            butterchickenIngre.add("onion");
            butterchickenIngre.add("lemon juice");
            butterchickenIngre.add("garlic");
            butterchickenIngre.add("tomato");
            butterchickenIngre.add("ginger");
            butterchickenIngre.add("yogurt");
            butterchickenIngre.add("cashews");


            for (int i = 0; i < butterchickenIngre.size(); i++) {
                dbHelper.ingredients_Insert(butterchickenId, butterchickenIngre.get(i));
            }

            //Set khamandhokla data
            drawable = getResources().getDrawable(R.drawable.khamandhokla, getActivity().getTheme());
            byte[] khamandhokla = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Khaman Dhokla", "Shubhnit", today.toString(),
                    "1.firstly, in a large mixing bowl sieve 1½ cup besan and 3 tbsp suji.\n" +
                            "2.add ½ tsp ginger paste, 2 chilli, ¼ tsp turmeric, 1 tsp sugar, pinch hing, ½ tsp salt, 1 tbsp lemon juice and 1 tbsp oil.\n" +
                            "3.prepare a smooth batter adding 1 cup of water or as required.\n" +
                            "4.additionally, add ½ tsp of eno fruit salt. you can alternatively use a pinch of baking soda. \n" +
                            "5.immediately steam the dhokla batter for 20 minutes.\n" +
                            "6.further, cut the dhokla and pour tempering. \n" +
                            "7.garnish the dhokla with 2 tbsp chopped coriander leaves and 2 tbsp fresh grated coconut. \n" +
                            "8.finally, serve instant khaman dhokla with green chutney and tamarind chutney \n",
                    "staple indian snack food",
                    khamandhokla, khamandhokla, 9);

            int khamandhoklaId = dbHelper.recipes_GetIdByName("Khaman Dhokla");
            ArrayList<String> khamandhoklaIngre = new ArrayList<>();
            khamandhoklaIngre.add("besan");
            khamandhoklaIngre.add("suji");
            khamandhoklaIngre.add("ginger");
            khamandhoklaIngre.add("cumin");
            khamandhoklaIngre.add("turmeric");
            khamandhoklaIngre.add("sugar");
            khamandhoklaIngre.add("hing");
            khamandhoklaIngre.add("lemon juice");
            khamandhoklaIngre.add("red chilli");
            khamandhoklaIngre.add("oil");
            khamandhoklaIngre.add("mustard");
            khamandhoklaIngre.add("sesame");
            khamandhoklaIngre.add("hing");
            khamandhoklaIngre.add("curry leaves");
            khamandhoklaIngre.add("coconut");



            for (int i = 0; i < khamandhoklaIngre.size(); i++) {
                dbHelper.ingredients_Insert(khamandhoklaId, khamandhoklaIngre.get(i));
            }

            //Set sarsonkasaag data
            drawable = getResources().getDrawable(R.drawable.sarsonkasaag, getActivity().getTheme());
            byte[] sarsonkasaag = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Sarson Ka Saag", "Shubhnit", today.toString(),
                    "1.firstly, wash and finely chop 1 bunch mustard leaves (sarson ke patte) and ½ bunch spinach (palak). also add bathua leaves if available.take the chopped leaves in pressure cooker along with 2 clove garlic, 1 inch ginger, ¼ onion, 2 chilli and ½ tsp salt.\n" +
                            "2.add 1 cup water and pressure cook for 4 whistles.mash until coarsely ground.\n" +
                            "3.further add 2 tbsp of makki ka atta and mix well.\n" +
                            "4.cook for 5 minutes or until the mixture thickens. keep aside. \n" +
                            "5.now in a large kadai, heat 2 tbsp ghee, and saute 2 clove garlic, 1 inch ginger and 1 green chilli.\n" +
                            "6.further saute 1 onion until it turns golden brown. \n" +
                            "7.add in cooked and mashed leaves and mix well.continue to cook for 4-5 minutes, or until the saag is cooked completely. \n" +
                            "8.finally, enjoy sarso ka saag with makki ki roti or with chapati. \n",
                    "staple indian main course dish",
                    sarsonkasaag, sarsonkasaag, 29);

            int sarsonkasaagId = dbHelper.recipes_GetIdByName("Sarson Ka Saag");
            ArrayList<String> sarsonkasaagIngre = new ArrayList<>();
            sarsonkasaagIngre.add("mustard");
            sarsonkasaagIngre.add("spinach");
            sarsonkasaagIngre.add("ginger");
            sarsonkasaagIngre.add("garlic");
            sarsonkasaagIngre.add("onion");
            sarsonkasaagIngre.add("cornmeal");
            sarsonkasaagIngre.add("hing");
            sarsonkasaagIngre.add("ghee");
            sarsonkasaagIngre.add("red chilli");




            for (int i = 0; i < sarsonkasaagIngre.size(); i++) {
                dbHelper.ingredients_Insert(sarsonkasaagId, sarsonkasaagIngre.get(i));
            }

            //Set chickentikkamasala data
            drawable = getResources().getDrawable(R.drawable.chickentikkamasala, getActivity().getTheme());
            byte[] chickentikkamasala = imageHelper.getByteArrayFromDrawable(drawable);


            dbHelper.recipes_Insert("india", "Chicken Tikka Masala", "Shubhnit", today.toString(),
                    "1.Combine yogurt, 2 teaspoons garam masala, paprika, black pepper, 1/2 teaspoon salt, cayenne pepper, and 1/2 teaspoon coriander in a large bowl. Add chicken strips and toss to coat. Cover and marinate in the refrigerator for 2 hours.\n" +
                            "2.Preheat oven to 450 degrees F (230 degrees C). Grease a baking sheet.\n" +
                            "3.Place chicken strips on the prepared baking sheet, leaving space between each piece, and bake in the preheated oven until browned and no longer pink inside, about 10 minutes. Remove and set aside.\n" +
                            "4.Heat vegetable oil in a large skillet over medium heat. Cook and stir cumin seeds until lightly toasted and aromatic, about 3 minutes. Add onion; cook and stir until onion begins to soften, 4 to 5 minutes. Stir in garlic, ginger, and green chiles and continue to cook until onion is browned, 15 to 20 minutes. Cook and stir tomatoes, tomato paste, and water into onion mixture until tomatoes begin to break down and incorporate into the onion mixture, about 10 minutes.\n" +
                            "5.Cook and stir 1 teaspoon garam masala, 1/2 teaspoon coriander, and turmeric into the tomato mixture. Mix in the cooked chicken, add cream, and stir to coat. \n" +
                            "6.Cover and let simmer for 10 minutes. Season with 1/2 teaspoon salt and garnish with cilantro.", "indian traditional food",
                    chickentikkamasala, chickentikkamasala, 23);

            int chickentikkamasalaId = dbHelper.recipes_GetIdByName("Chicken Tikka Masala");
            ArrayList<String> chickentikkamasalaIngre = new ArrayList<>();
            chickentikkamasalaIngre.add("yogurt");
            chickentikkamasalaIngre.add("garam masala");
            chickentikkamasalaIngre.add("pepper");
            chickentikkamasalaIngre.add("coriander");
            chickentikkamasalaIngre.add("chicken");
            chickentikkamasalaIngre.add("oil");
            chickentikkamasalaIngre.add("cumin");
            chickentikkamasalaIngre.add("onion");
            chickentikkamasalaIngre.add("garlic");
            chickentikkamasalaIngre.add("ginger");
            chickentikkamasalaIngre.add("green chilli");
            chickentikkamasalaIngre.add("tomato");
            chickentikkamasalaIngre.add("garam masala");
            chickentikkamasalaIngre.add("turmeric");
            chickentikkamasalaIngre.add("cream");


            for (int i = 0; i < chickentikkamasalaIngre.size(); i++) {
                dbHelper.ingredients_Insert(chickentikkamasalaId, chickentikkamasalaIngre.get(i));
            }

            //Set samosa data
            drawable = getResources().getDrawable(R.drawable.samosa, getActivity().getTheme());
            byte[] samosa = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "Samosa", "Shubhnit", today.toString(),
                    "1.pinch a ball sized dough and grease with oil.roll the dough into oval shape.\n" +
                            "2.now cut it horizontally, diving into 2 equal portions using a knife.\n" +
                            "3.grease with water and make cone.\n" +
                            "4.stuff 2 tbsp of prepared samosa masala into the cone. \n" +
                            "5.grease little water on the edges.\n" +
                            "6.close and seal tightly by pressing firmly. \n" +
                            "7.deep-fry the samosa on a low flame. alternatively bake at 180 degree celcius for 40 minutes. \n" +
                            "8.stir occasionally, frying the samosa on low flame for atleast 15 minutes.once the aloo samosa turns golden and crisp, drain off over kitchen paper. \n" +
                            "9.finally, enjoy aloo samosa with green chutney and tamarind chutney. \n",
                    "staple indian snack food",
                    samosa, samosa, 19);

            int samosaId = dbHelper.recipes_GetIdByName("Samosa");
            ArrayList<String> samosaIngre = new ArrayList<>();
            samosaIngre.add("maida");
            samosaIngre.add("carom");
            samosaIngre.add("oil");
            samosaIngre.add("cumin");
            samosaIngre.add("coriander");
            samosaIngre.add("fennel");
            samosaIngre.add("hing");
            samosaIngre.add("ginger");
            samosaIngre.add("red chilli");
            samosaIngre.add("peas");
            samosaIngre.add("dry mango powder");
            samosaIngre.add("garam masala");
            samosaIngre.add("pepper");
            samosaIngre.add("potato");
            samosaIngre.add("cashew");
            samosaIngre.add("raisins");


            for (int i = 0; i < samosaIngre.size(); i++) {
                dbHelper.ingredients_Insert(samosaId, samosaIngre.get(i));
            }

            //Set chickenfriedrice data
            drawable = getResources().getDrawable(R.drawable.chickenfriedrice, getActivity().getTheme());
            byte[] chickenfriedrice = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("china", "chicken fried rice", "Shubhnit", today.toString(),
                    "1.soak the rice in water for at least 20 minutes before starting. Wash and marinate the chicken in some salt and black pepper for 20 minutes. Now, add 2 cups of water in a pan and let it come to a boil. To this, add the rice and let them cook until 80% done. Drain the rice and keep aside.\n" +
                            "2. Now that the chicken has been marinated, add some oil to a hot pan and add in the chicken. Cook it until it changes colour to a light golden. Take it out and keep aside..\n" +
                            "3. Now add 1 tbsp of oil into the pan again and stir fry the finely chopped garlic and add the onion. Cook this for 35 seconds and add in the rest of the vegetables and cook on high flame for 1-2 minutes.Add the sauces, chicken, rice and serve!",
                    "traditional chinese recipe.",
                    chickenfriedrice, chickenfriedrice, 6);

            int chickenfriedriceId = dbHelper.recipes_GetIdByName("chicken fried rice");
            ArrayList<String> chickenfriedriceIngre = new ArrayList<>();
            chickenfriedriceIngre.add("chicken");
            chickenfriedriceIngre.add("rice");
            chickenfriedriceIngre.add("garlic");
            chickenfriedriceIngre.add("onion");
            chickenfriedriceIngre.add("soy sauce");
            chickenfriedriceIngre.add("carrot");
            chickenfriedriceIngre.add("vinegar");
            chickenfriedriceIngre.add("vegetable oil");


            for (int i = 0; i < chickenfriedriceIngre.size(); i++) {
                dbHelper.ingredients_Insert(chickenfriedriceId, chickenfriedriceIngre.get(i));
            }

            //Set manchurian data
            drawable = getResources().getDrawable(R.drawable.manchurian, getActivity().getTheme());
            byte[] manchurian = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("china", "Manchurian", "Shubhnit", today.toString(),
                    "1. Take all the veggies, finely chopped, in a bowl along with ginger, garlic, corn flour, maida, salt, black pepper and soy sauce.Mix them well, adding water as required for consistency.Make round balls out of the thick vegetable mixture.\n" +
                            "2. Finally,  deep fry the vegetable balls in a pan.\n" +
                            "3. Heat some oil in a pan and roast ginger, garlic and green chilli in it for a while.Add spring onion, tomato ketchup, chilly sauce, soya sauce and vinegar followed by salt and black pepper.Mix them well and add a mixture of corn flour plus water to the pan.\n"+
                            "4. Mix thoroughly and put the fried vegetable balls into the mixture.Garnish with spring onion and celery. Serve hot along with cooked rice.",
                    "traditional chinese food.",
                    manchurian, manchurian, 5);

            int manchurianId = dbHelper.recipes_GetIdByName("Manchurian");
            ArrayList<String> manchurianIngre = new ArrayList<>();
            manchurianIngre.add("cabbage");
            manchurianIngre.add("ginger");
            manchurianIngre.add("carrot");
            manchurianIngre.add("onion");
            manchurianIngre.add("tomato");
            manchurianIngre.add("garlic");
            manchurianIngre.add("maida");
            manchurianIngre.add("capsicum");
            manchurianIngre.add("soy sauce");
            manchurianIngre.add("green chilli");
            manchurianIngre.add("vinegar");
            manchurianIngre.add("corn flour");






            for (int i = 0; i < manchurianIngre.size(); i++) {
                dbHelper.ingredients_Insert(manchurianId, manchurianIngre.get(i));
            }
            //Set paneerbhurji data
            drawable = getResources().getDrawable(R.drawable.paneerbhurji, getActivity().getTheme());
            byte[] paneerbhurji = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "paneer bhurji", "Shubhnit", today.toString(),
                    "1. To prepare this delicious dish, finely chop the capsicum in a bowl. Next, finely chop the ginger as well in a separate bowl.Put a pan over medium flame and heat oil in it. Once the oil is hot enough, add chopped capsicum in it. Saute for a minute and then add the ginger. Stir and cook the ginger as well for a minute.\n" +
                            "2. Finally, add the grated paneer in the pan and cook for a minute.Next, add the makhni gravy along with the onion tomato masala in the pan and stir to mix well all the ingredients. Let it cook for 4-5 minutes.\n" +
                            "3. Once the bhurji is done, put fresh cream, butter and a pinch of kassori methi over the dish. Garnish with coriander sprigs and tomato wedges. Serve hot with your favorite bread.",
                    "traditional indian food.",
                    paneerbhurji, paneerbhurji, 12);

            int paneerbhurjiId = dbHelper.recipes_GetIdByName("paneer bhurji");
            ArrayList<String> paneerbhurjiIngre = new ArrayList<>();
            paneerbhurjiIngre.add("paneer");
            paneerbhurjiIngre.add("ginger");
            paneerbhurjiIngre.add("vegetable oil");
            paneerbhurjiIngre.add("onion");
            paneerbhurjiIngre.add("tomato");
            paneerbhurjiIngre.add("butter");
            paneerbhurjiIngre.add("vegetable oil");
            paneerbhurjiIngre.add("capsicum");
            paneerbhurjiIngre.add("gravy");

            for (int i = 0; i < paneerbhurjiIngre.size(); i++) {
                dbHelper.ingredients_Insert(paneerbhurjiId, paneerbhurjiIngre.get(i));
            }


            //Set masaladosa data
            drawable = getResources().getDrawable(R.drawable.masaladosa, getActivity().getTheme());
            byte[] masaladosa = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "masala dosa", "Shubhnit", today.toString(),
                    "1.To prepare the batter for Masala Dosa, wash and soak the rice (with fenugreek added into it) and urad dal in separate containers for approximately 6-8 hours. Once the rice and urad dal are soaked well, grind them separately in a mixer using the water in which they were soaked, till the mixture reaches a smooth consistency. Mix the batter of both the ingredients in a bigger container and add salt to it. Combine well and allow it to ferment overnight.\n" +
                            "2. To prepare the filling of the dosa, heat 2 tbsp oil in a thick-bottomed pan and let the mustard seeds splutter. Then, add sliced onions, curry leaves, green chillies and saute them till the onions turn pink. Then, add a pinch of salt, turmeric powder and mix them well. Now, take the cubed potatoes and add them to the sauteed onions and mix them together. Pour water gradually into the mixture and allow the potatoes to simmer for around 4 minutes. When the mixture is in semi-thick state, turn off the gas and let it rest for a few seconds.\n" +
                            "3. Now, take a dosa tawa and heat it on low-medium flame. Smear 1 tsp oil on it to prepare the dosa. When the tawa is hot, pour and spread the batter evenly in a circular motion.When the colour of dosa's edges turns into brown, reduce the flame and sprinkle few drops of oil on the dosa sides and put 2 tablespoons of filling. Fold the dosa. Repeat the process until all the batter and filling is used up. Serve hot Masala Dosa with coconut chutney and sambhar.",
                    "traditional indian food.",
                    masaladosa, masaladosa, 13);

            int masaladosaId = dbHelper.recipes_GetIdByName("masala dosa");
            ArrayList<String> masaladosaIngre = new ArrayList<>();
            masaladosaIngre.add("rice");
            masaladosaIngre.add("vegetable oil");
            masaladosaIngre.add("urad");
            masaladosaIngre.add("potato");
            masaladosaIngre.add("onion");
            masaladosaIngre.add("green chilli");
            masaladosaIngre.add("curry leaves");
            masaladosaIngre.add("turmeric");

            for (int i = 0; i < masaladosaIngre.size(); i++) {
                dbHelper.ingredients_Insert(masaladosaId, masaladosaIngre.get(i));
            }



            //Set jeeraaloo data
            drawable = getResources().getDrawable(R.drawable.jeeraaloo, getActivity().getTheme());
            byte[] jeeraaloo = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("india", "jeera aloo", "Shubhnit", today.toString(),
                    "1. firstly, heat 2 tsp oil and saute 1 tbsp cumin seeds till it turns aromatic.add in 1 inch ginger and 2 green chilli. saute for a minute.further keeping the flame low, add ½ tsp turmeric, 1 tsp chilli powder, ½ tsp coriander powder, ¾ tsp aamchur, pinch of hing and salt to taste. \n" +
                            "2. saute on low flame without burning the spices.additionally add 2 boiled and cubed potatoes. (i have pressure cooked potatoes for 2 whistles) \n" +
                            "3. mix gently without breaking potatoes.add 2 tbsp of water, cover and simmer for 5 minutes. \n" +
                            "4. after 5 minutes, potatoes have absorbed all spices.finally, add coriander leaves and serve jeera aloo with rice or chapati  \n",
                    "staple indian food",
                    jeeraaloo, jeeraaloo, 8);

            int jeeraalooId = dbHelper.recipes_GetIdByName("jeera aloo");
            ArrayList<String> jeeraalooIngre = new ArrayList<>();
            jeeraalooIngre.add("vegetable oil");
            jeeraalooIngre.add("jeera");
            jeeraalooIngre.add("ginger");
            jeeraalooIngre.add("green chilli");
            jeeraalooIngre.add("turmeric");
            jeeraalooIngre.add("red chilli");
            jeeraalooIngre.add("coriander");
            jeeraalooIngre.add("dry mango powder");
            jeeraalooIngre.add("hing");
            jeeraalooIngre.add("potato");


            for (int i = 0; i < jeeraalooIngre.size(); i++) {
                dbHelper.ingredients_Insert(jeeraalooId, jeeraalooIngre.get(i));
            }
        }



        TextView resultTextView = (TextView) view.findViewById(R.id.txt_DBresult);
        //resultTextView.setText(tempcategory);

        newList = dbHelper.recipes_SelectNew();

        newGridView.setAdapter(new MainRecipeAdapter(this.getContext(), newList, R.layout.fragment_home_recipeitem));

        newGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = newList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipe", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipeName(),Toast.LENGTH_SHORT).show();
            }
        });


        //connect GrieView code to UI
        GridView bestGridView = (GridView) view.findViewById(R.id.GridView_Best);

        bestList = dbHelper.recipes_SelectBest();

        bestGridView.setAdapter(new MainRecipeAdapter(this.getContext(), bestList, R.layout.fragment_home_recipeitem));

        bestGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = bestList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipe", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipName(), Toast.LENGTH_SHORT).show();
            }
        });

        GridView fullListView = (GridView) view.findViewById(R.id.GridView_full);

        fullList = dbHelper.recipes_SelectAll();

        fullListView.setAdapter(new MainRecipeAdapter(this.getContext(), fullList, R.layout.fragment_home_recipeitem));

        fullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = fullList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipe", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
