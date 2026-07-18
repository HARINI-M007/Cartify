package com.example.cartify;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class WishlistFragment extends Fragment {

    RecyclerView recyclerWishlist;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_wishlist,
                container,
                false);

        recyclerWishlist = view.findViewById(R.id.recyclerWishlist);

        recyclerWishlist.setLayoutManager(
                new LinearLayoutManager(getContext()));

        ArrayList<WishlistModel> wishlistList = new ArrayList<>();

        WishlistAdapter adapter = new WishlistAdapter(wishlistList);

        recyclerWishlist.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            String uid = FirebaseAuth.getInstance()
                    .getCurrentUser()
                    .getUid();

            db.collection("users")
                    .document(uid)
                    .collection("wishlist")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        wishlistList.clear();

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                            WishlistModel wishlist =
                                    document.toObject(WishlistModel.class);

                            wishlistList.add(wishlist);

                        }

                        adapter.notifyDataSetChanged();

                    });
        }

        return view;
    }
}