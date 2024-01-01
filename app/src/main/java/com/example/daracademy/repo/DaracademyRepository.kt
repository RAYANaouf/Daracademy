package com.example.daracademy.repo

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.daracademy.model.dataClasses.ChatInfo
import com.example.daracademy.model.dataClasses.Course
import com.example.daracademy.model.dataClasses.Formation
import com.example.daracademy.model.dataClasses.Matiere
import com.example.daracademy.model.dataClasses.MatiereWithCourses
import com.example.daracademy.model.dataClasses.Message
import com.example.daracademy.model.dataClasses.MessageBox
import com.example.daracademy.model.dataClasses.Post
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.sealedClasses.Errors.Errors
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_C_E_M
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_lycee
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_primaire
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DaracademyRepo {


    private val auth: FirebaseAuth by mutableStateOf(Firebase.auth)
    private val firebaseFirestore     = Firebase.firestore
    private val firebaseStorageRef    = Firebase.storage.reference

    private var chatListener          : ListenerRegistration? = null
    private var chatBoxListener       : ListenerRegistration? = null
    var chatBoxs                      = mutableListOf<MessageBox>()



    //matiere && its courses
    var matieres : HashMap<String , HashMap< String , List<MatiereWithCourses>? > > =
        hashMapOf(
            PhaseDesEtudes.Primaire().phase to hashMapOf(
                annees_de_primaire[0].id     to null,
                annees_de_primaire[1].id     to null,
                annees_de_primaire[2].id     to null,
                annees_de_primaire[3].id     to null,
                annees_de_primaire[4].id     to null,
                annees_de_primaire[5].id     to null,
                annees_de_primaire[6].id     to null,
            ),
            PhaseDesEtudes.CEM().phase      to hashMapOf(
                annees_de_C_E_M[0].id        to null,
                annees_de_C_E_M[1].id        to null,
                annees_de_C_E_M[2].id        to null,
                annees_de_C_E_M[3].id        to null,
                annees_de_C_E_M[4].id        to null,
            ),
            PhaseDesEtudes.Lycee().phase    to hashMapOf(
                annees_de_lycee[0].id        to null,
                annees_de_lycee[1].id        to null,
                annees_de_lycee[2].id        to null,
                annees_de_lycee[3].id        to null,
            )

        )

    //teachers
    var teachers : List<Teacher> = emptyList()

    constructor(){

    }


    fun setChatBoxsListener(userId: String , onSuccessCallBack: (List<MessageBox>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}){

        chatBoxListener?.remove()

        chatBoxListener = firebaseFirestore.collection("chats")
            .where(Filter.equalTo("userId" , userId))
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }

                if (snapshot == null || snapshot.isEmpty){
                    return@addSnapshotListener
                }

                val newChatBoxs = snapshot.documents.mapNotNull { msg->
                    val messageBox = msg.toObject(MessageBox::class.java)
                    messageBox?.copy(productId = msg.id.split("_")[1])

                }.toList()

                chatBoxs = newChatBoxs.toMutableList()

                onSuccessCallBack(chatBoxs)
            }
    }

    fun getAllFormation(onSuccessCallBack: (List<Formation>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        firebaseFirestore.collection("formations")
            .get()
            .addOnSuccessListener { result->

                val formations = ArrayList<Formation>()

                for (doc in result){
                    formations.add(doc.toObject(Formation::class.java))
                }

                onSuccessCallBack(formations)

            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }

    }


    fun getAllPosts(onSuccessCallBack: (List<Post>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        firebaseFirestore.collection("posts")
            .get()
            .addOnSuccessListener { result->

                val formations = ArrayList<Post>()

                for (doc in result){
                    formations.add(doc.toObject(Post::class.java))
                }

                onSuccessCallBack(formations)

            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }

    }


    fun getBoxMessages(userId : String , productId : String ,  onSuccessCallBack: (List<Message>) -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){


        val chatBoxDoc = firebaseFirestore.collection("chats").document("${userId}_${productId}")

        chatListener?.remove()

        chatListener = chatBoxDoc.collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null){
                    onFailureCallBack(error)
                    return@addSnapshotListener
                }
                if (snapshot == null || snapshot.isEmpty){
                    return@addSnapshotListener
                }

                val messages = snapshot.documents.mapNotNull { msg->
                    msg.toObject(Message::class.java)
                }
                onSuccessCallBack(messages)

            }


    }


    fun createChatBox(chatInfo: ChatInfo , productId: String , onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        val chatBoxRef = firebaseFirestore.collection("chats").document("${chatInfo.id}_${productId}")


        //lastMessage
        chatBoxRef.set(
            mapOf(
                "userId"      to chatInfo.id,
                "name"        to chatInfo.name,
                "timestamp"   to FieldValue.serverTimestamp()
            ),
            SetOptions.merge()
        )
            .addOnFailureListener {
                onFailureCallBack(it)
            }
            .addOnSuccessListener {
                onSuccessCallBack()
            }



    }


    fun sendMsg(userId : String , productId: String , newMassage : Message, onSuccessCallBack: () -> Unit = {}, onFailureCallBack: (exp : Exception) -> Unit = {}  ){

        val chatBoxRef = firebaseFirestore.collection("chats").document("${userId}_${productId}")
        val messagesRef = chatBoxRef.collection("messages")

        val id = System.currentTimeMillis().toString()

        if (newMassage.photo != ""){
            val imageRef = firebaseStorageRef.child("messagePhotos/user_$userId/product_$productId/$id")

            imageRef.putFile(Uri.parse(newMassage.photo))
                .addOnSuccessListener {

                    imageRef.downloadUrl
                        .addOnFailureListener { onFailureCallBack(it) }
                        .addOnSuccessListener {storageUri->
                            messagesRef
                                .document()
                                .set(
                                    hashMapOf(
                                        "msg"        to  newMassage.msg,
                                        "photo"      to (storageUri?.toString() ?: ""),
                                        "person_msg" to  newMassage.person_msg,
                                        "timestamp"  to  FieldValue.serverTimestamp(),
                                    )
                                )
                                .addOnSuccessListener(){

                                    //lastMessage
                                    chatBoxRef.set(
                                        mapOf(
                                            "lastMessage"  to  newMassage.msg,
                                            "photo"        to  "photo",
                                            "person_msg"   to newMassage.person_msg,
                                            "timestamp"    to  FieldValue.serverTimestamp()
                                        ),
                                        SetOptions.merge()
                                    )
                                        .addOnFailureListener {
                                            onFailureCallBack(it)
                                        }
                                        .addOnSuccessListener {
                                            onSuccessCallBack()
                                        }

                                }
                                .addOnFailureListener(onFailureCallBack)

                        }


                }
                .addOnFailureListener(onFailureCallBack)
        }
        else{
            messagesRef
                .document()
                .set(
                    hashMapOf(
                        "msg"        to  newMassage.msg,
                        "person_msg" to  newMassage.person_msg,
                        "timestamp"  to  FieldValue.serverTimestamp(),
                    )
                )
                .addOnSuccessListener(){

                    //lastMessage
                    chatBoxRef.set(
                        mapOf(
                            "lastMessage"  to newMassage.msg,
                            "person_msg"   to  newMassage.person_msg,
                            "timestamp"    to FieldValue.serverTimestamp()
                        ),
                        SetOptions.merge()
                    )
                        .addOnFailureListener {
                            onFailureCallBack(it)
                        }
                        .addOnSuccessListener {
                            onSuccessCallBack()
                        }

                }
                .addOnFailureListener(onFailureCallBack)


        }

    }



    fun getAllMatieres(phase : String , annee : String , onSuccessCallBack: (List<Matiere>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit) {


        if (matieres.get(phase)?.get(annee) != null){
            onSuccessCallBack(matieres.get(phase)?.get(annee)!!.map{it.matiere ?: Matiere()} )
        }

        else{
            firebaseFirestore.collection("phases")
                .document(phase)
                .collection("annees")
                .document(annee)
                .collection("matieres")
                .get()
                .addOnSuccessListener { docs->
                    var new_matieres = ArrayList<Matiere>()
                    var new_matiereswithCourses = ArrayList<MatiereWithCourses>()
                    for (doc in docs){
                        val matiere = doc.toObject(Matiere::class.java)
                        new_matieres.add(matiere)
                        new_matiereswithCourses.add(MatiereWithCourses(matiere = matiere))
                    }

                    matieres.get(phase)?.set(annee , new_matiereswithCourses)

                    onSuccessCallBack(new_matieres)
                }
                .addOnFailureListener {
                    onFailureCallBack(it)
                }
        }


    }


    fun getCourses(phase : String , annee : String , matiere_name : String ,  onSuccessCallBack: (List<Course>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){


        var coursesList : List<Course>? = null
        var matiereIdex  = 0

        matieres[phase]?.get(annee)?.forEachIndexed {index , item ->

            if (item.matiere?.name == matiere_name){
                matiereIdex = index
                coursesList = item.courses
            }

        }

        if (coursesList != null){

            onSuccessCallBack(coursesList!!)

            return@getCourses
        }

        firebaseFirestore.collection("phases")
            .document(phase)
            .collection("annees")
            .document(annee)
            .collection("matieres")
            .document(matiere_name)
            .collection("courses")
            .get()
            .addOnSuccessListener { docs->

                var courses = ArrayList<Course>()

                for (doc in docs){
                    courses.add(doc.toObject(Course::class.java))
                }

                //now we get the courses
                //change the courses value in the hashmap
                if (courses.isEmpty()){

                    matieres.get(phase)?.get(annee)?.get(matiereIdex)?.courses = null
                    onSuccessCallBack(courses)
                }
                else {
                    matieres.get(phase)?.get(annee)?.get(matiereIdex)?.courses = courses
                    onSuccessCallBack(courses)
                }



            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }
    }


    fun getTeacherById(teacherId : String , onSuccessCallBack: (Teacher?) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        var selectedTeacher : Teacher? = isTeacherExist(teacherId = teacherId)

        if (selectedTeacher != null){
            onSuccessCallBack(isTeacherExist(teacherId = teacherId))
            return@getTeacherById
        }

        firebaseFirestore.collection("teachers")
            .get()
            .addOnSuccessListener {docs->


                for (doc in docs){
                    var teacher : Teacher? = null

                    teacher = doc.toObject(Teacher::class.java)

                    if (isTeacherExist(teacherId = teacher?.id ?: "") == null){
                        var teachersMutable = this.teachers.toMutableList()
                        teachersMutable.add(teacher!!)
                        this.teachers = teachersMutable
                    }

                    if (teacher?.id == teacherId ){
                        selectedTeacher = teacher
                    }
                }

                if (selectedTeacher == null){
                    onFailureCallBack(Errors.TeachersDidntExistError())
                }
                else{
                    onSuccessCallBack(selectedTeacher)
                }

            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }

    }

    fun getAllAvailableTeachers() : List<Teacher>{
        return this.teachers
    }

    fun isTeacherExist(teacherId : String): Teacher?{

        this.teachers.forEach {
            if (it.id == teacherId)
                return it
        }

        return null
    }

}