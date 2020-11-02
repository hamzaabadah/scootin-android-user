package com.scootin.view.fragment.orders

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.scootin.R
import com.scootin.databinding.FragmentOrderHistoryBinding
import com.scootin.network.ApiClient
import com.scootin.network.AppExecutors
import com.scootin.network.api.APIService
import com.scootin.network.api.ApiOrdersService
import com.scootin.network.manager.AppHeaders
import com.scootin.network.response.orders.OrderHistoryList
import com.scootin.util.fragment.autoCleared
import com.scootin.view.adapter.order.OrderHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class OrderHistoryFragment: Fragment(R.layout.fragment_order_history) {
    private var binding by autoCleared<FragmentOrderHistoryBinding>()

    @Inject
    lateinit var appExecutors: AppExecutors
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderHistoryBinding.bind(view)
        setAdaper()

        setList()


    }

    private fun setupListener() {
        binding.back.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setAdaper() {
        orderHistoryAdapter =
            OrderHistoryAdapter(
                appExecutors,
                object : OrderHistoryAdapter.ImageAdapterClickLister {
                    override fun onViewDetailsSelected(view: View) {

                    }
                })

        binding.orderList.apply {
            adapter = orderHistoryAdapter
        }
    }

    fun getData(list: List<OrderHistoryList>): List<OrderHistoryList> {
        return list
    }

     fun setList(): ArrayList<OrderHistoryList> {

        var list = ArrayList<OrderHistoryList>()

        val apiOrdersService:ApiOrdersService =
            ApiClient.getRetrofitInstance()!!.create(ApiOrdersService::class.java)

        val call =
            apiOrdersService.getAllOrdersForUser(12,0,50)
        //Log.e(" Login url", call.request().url.toString());
        call.enqueue(object :Callback<List<OrderHistoryList>> {

            override fun onFailure(call: Call<List<OrderHistoryList>>, t: Throwable) {
                Log.i("hamza","error")
                Log.i("hamza", t.message)

            }

            override fun onResponse(
                call: Call<List<OrderHistoryList>>,
                response: Response<List<OrderHistoryList>>
            ) {
                if (response.isSuccessful){
                    list = response.body() as ArrayList<OrderHistoryList>
                    if (list.size>0){
                        setupListener()
                        orderHistoryAdapter.submitList(list)
                    }
                }
            }

        })
        return list
    }


}