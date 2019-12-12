package com.example.home_med

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home_med.databinding.FragmentLocalMedicationBinding
import com.example.home_med.models.m_LocalMedication
import com.example.home_med.viewHolder.medicationViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_local_medication.*
import kotlinx.android.synthetic.main.fragment_profile.*

class LocalMedication : Fragment() {

    private var medAdapter: FirestoreRecyclerAdapter<m_LocalMedication, medicationViewHolder>? = null
    private var medAdapterInactive: FirestoreRecyclerAdapter<m_LocalMedication, medicationViewHolder>? = null

    private var firestoreDB: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null
    private var medList = mutableListOf<m_LocalMedication>()
    private var medListInactive = mutableListOf<m_LocalMedication>()
    lateinit var firebaseAuth: FirebaseAuth

    override fun onDestroy() {
        super.onDestroy()
        firestoreListener!!.remove()
    }
    override fun onStart() {
        super.onStart()
        medAdapterInactive!!.startListening()
        medAdapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        medAdapterInactive!!.stopListening()
        medAdapter!!.stopListening()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLocalMedicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_local_medication, container, false)
        firestoreDB = FirebaseFirestore.getInstance()

        val mLayoutManager = LinearLayoutManager(context)
        val mLayoutManagerInactive = LinearLayoutManager(context)

        firebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = firebaseAuth.getCurrentUser()
        val currentUserId = user!!.uid

        val userEmail = user.email.toString()

        val query = firestoreDB!!.collection("Medication").whereEqualTo("m_userID", userEmail)

        val response = FirestoreRecyclerOptions.Builder<m_LocalMedication>()
            .setQuery(query, m_LocalMedication::class.java)
            .build()

        medAdapter = object : FirestoreRecyclerAdapter<m_LocalMedication, medicationViewHolder>(response) {
            override fun onBindViewHolder(holder: medicationViewHolder, position: Int, model: m_LocalMedication) {
                val note = medList[position]

                holder.medicationName.text = note.m_medicationName
                holder.medicationType.text = note.m_medicationType
                holder.medicationQty.text = note.m_medicationQty
                holder.viewMedicationButton.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationQty.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationType.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationName.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medicationViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)

                return medicationViewHolder(view)
            }

            override fun onError(e: FirebaseFirestoreException) {
                Log.e("error", e!!.message)
            }
        }

        val queryInactive = firestoreDB!!.collection("Medication").whereEqualTo("m_medicationStatus", false)
        //queryInactive = queryInactive.whereEqualTo("m_userID", userEmail)

        val responseInactive = FirestoreRecyclerOptions.Builder<m_LocalMedication>()
            .setQuery(queryInactive, m_LocalMedication::class.java)
            .build()

        medAdapterInactive = object : FirestoreRecyclerAdapter<m_LocalMedication, medicationViewHolder>(responseInactive) {
            override fun onBindViewHolder(holder: medicationViewHolder, position: Int, model: m_LocalMedication) {
                val note = medListInactive[position]

                holder.medicationName.text = note.m_medicationName
                holder.medicationType.text = note.m_medicationType
                holder.medicationQty.text = note.m_medicationQty
                holder.viewMedicationButton.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationQty.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationType.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
                holder.medicationName.setOnClickListener { v: View ->
                    v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToViewMedication(note.m_medicationName.toString()))
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medicationViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)

                return medicationViewHolder(view)
            }

            override fun onError(e: FirebaseFirestoreException) {
                Log.e("error", e!!.message)
            }
        }

        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = medAdapter
        }

        binding.recyclerviewInactive.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManagerInactive
            adapter = medAdapterInactive
        }

        medAdapter!!.notifyDataSetChanged()
        recyclerview?.adapter = medAdapter

        medAdapterInactive!!.notifyDataSetChanged()
        recyclerviewInactive?.adapter = medAdapterInactive

        firestoreListener = firestoreDB!!.collection("Medication").whereEqualTo("m_medicationStatus", true)
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e(TAG, "Listen failed!", e)
                    return@EventListener
                }

                medList = mutableListOf()

                if (documentSnapshots != null) {
                    for (doc in documentSnapshots) {
                        val note = doc.toObject(m_LocalMedication::class.java)
                        note.m_medicationName = doc.id
                        medList.add(note)
                    }
                }
                medAdapter!!.notifyDataSetChanged()
                recyclerview?.adapter = medAdapter
            })

        firestoreListener = firestoreDB!!.collection("Medication").whereEqualTo("m_medicationStatus", false)
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e(TAG, "Listen failed!", e)
                    return@EventListener
                }

                medListInactive = mutableListOf()

                if (documentSnapshots != null) {
                    for (doc in documentSnapshots) {
                        val note = doc.toObject(m_LocalMedication::class.java)
                        note.m_medicationName = doc.id
                        medListInactive.add(note)
                    }
                }
                medAdapterInactive!!.notifyDataSetChanged()
                recyclerviewInactive?.adapter = medAdapterInactive
            })


        binding.addMedicationButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToAddMedication())
        }
        binding.homeButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LocalMedicationDirections.actionLocalMedicationToHome2())
        }
        setHasOptionsMenu(true)

        return binding.root
    }
}