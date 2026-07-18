package com.example.cartify;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    RecyclerView recyclerOrders;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_orders,
                container,
                false);

        recyclerOrders =
                view.findViewById(R.id.recyclerOrders);

        recyclerOrders.setLayoutManager(
                new LinearLayoutManager(getContext()));

        ArrayList<OrderModel> orderList = new ArrayList<>();

        OrderAdapter adapter = new OrderAdapter(orderList);

        recyclerOrders.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            String uid = FirebaseAuth.getInstance()
                    .getCurrentUser()
                    .getUid();

            db.collection("users")
                    .document(uid)
                    .collection("orders")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {

                        orderList.clear();

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                            OrderModel order =
                                    document.toObject(OrderModel.class);

                            orderList.add(order);
                        }

                        adapter.notifyDataSetChanged();

                    });

        }

        return view;
    }
}