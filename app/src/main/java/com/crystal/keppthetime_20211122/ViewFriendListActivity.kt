package com.crystal.keppthetime_20211122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.crystal.keppthetime_20211122.adapters.MyFriendsRecyclerAdapter
import com.crystal.keppthetime_20211122.databinding.ActivityViewFriendListBinding
import com.crystal.keppthetime_20211122.datas.BasicResponse
import com.crystal.keppthetime_20211122.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewFriendListActivity : BaseActivity() {

    lateinit var binding : ActivityViewFriendListBinding

    val mMyFridendsList = ArrayList<UserData>()

    lateinit var mMyFriendsAdapter: MyFriendsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_friend_list)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {
    }

    override fun setValues() {

        getMyFriendsFromServer()

        mMyFriendsAdapter = MyFriendsRecyclerAdapter(mContext, mMyFridendsList)
        binding.myFriendsRecyclerView.adapter = mMyFriendsAdapter

//        여러 형태로 목록 배치 가능. -> 어떤 형태로 보여줄건지? 리싸이클러뷰에 세팅.
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(mContext)



    }

    fun getMyFriendsFromServer() {

        apiService.getRequestMyFriends("my").enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

                    mMyFridendsList.addAll(br.data.friends)

                    mMyFriendsAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {


            }


        })

    }


}