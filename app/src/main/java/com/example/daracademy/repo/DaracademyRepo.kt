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
import com.example.daracademy.model.dataClasses.Student
import com.example.daracademy.model.dataClasses.Teacher
import com.example.daracademy.model.sealedClasses.Errors.Errors
import com.example.daracademy.model.sealedClasses.userType.UserType
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_C_E_M
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_lycee
import com.example.daracademy.model.variables.les_annees_d_etude.annees_de_primaire
import com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes.PhaseDesEtudes
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DaracademyRepo {


    private val auth: FirebaseAuth by mutableStateOf(Firebase.auth)
    private val firebaseFirestore     = Firebase.firestore
    private val firebaseStorageRef    = Firebase.storage.reference
    private val analytics             =  Firebase.analytics



    private var chatListener          : ListenerRegistration? = null
    var  chatMessages                 = mutableListOf<Message>()
        private set
    private var chatBoxListener       : ListenerRegistration? = null
    var chatBoxs                      = mutableListOf<MessageBox>()



    var matieres : HashMap<String , List<Matiere>? > =
        hashMapOf(
            PhaseDesEtudes.Primaire().phase+"_"+annees_de_primaire[0].id to null,
            PhaseDesEtudes.Primaire().phase+"_"+annees_de_primaire[1].id to null,
            PhaseDesEtudes.Primaire().phase+"_"+annees_de_primaire[2].id to null,
            PhaseDesEtudes.Primaire().phase+"_"+annees_de_primaire[3].id to null,
            PhaseDesEtudes.Primaire().phase+"_"+annees_de_primaire[4].id to null,

            PhaseDesEtudes.CEM().phase+"_"+annees_de_C_E_M[0].id to null,
            PhaseDesEtudes.CEM().phase+"_"+annees_de_C_E_M[1].id to null,
            PhaseDesEtudes.CEM().phase+"_"+annees_de_C_E_M[2].id to null,
            PhaseDesEtudes.CEM().phase+"_"+annees_de_C_E_M[3].id to null,
            PhaseDesEtudes.CEM().phase+"_"+annees_de_C_E_M[4].id to null,

            PhaseDesEtudes.Lycee().phase+"_"+annees_de_lycee[0].id to null,
            PhaseDesEtudes.Lycee().phase+"_"+annees_de_lycee[1].id to null,
            PhaseDesEtudes.Lycee().phase+"_"+annees_de_lycee[2].id to null,
            PhaseDesEtudes.Lycee().phase+"_"+annees_de_lycee[3].id to null,
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
            .orderBy("timestamp" , Query.Direction.DESCENDING)
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

                chatMessages = messages.toMutableList()

                onSuccessCallBack(chatMessages)

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


        matieres[phase+"_"+annee]?.let {
            onSuccessCallBack(it)
            return@getAllMatieres
        }

        firebaseFirestore
            .collection("matieres")
            .whereEqualTo("phase" , phase)
            .whereEqualTo("annee" , annee)
            .get()
            .addOnSuccessListener { docs->
                var new_matieres = ArrayList<Matiere>()
                for (doc in docs){
                    val matiere = doc.toObject(Matiere::class.java)
                    new_matieres.add(matiere)
                }

                matieres[phase+"_"+annee] =  new_matieres.toList()

                onSuccessCallBack(new_matieres)
            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }


    }

    fun getMatieresById( matiereId: String , onSuccessCallBack: (Matiere) -> Unit , onFailureCallBack: (ex: Exception) -> Unit) {


        firebaseFirestore
            .collection("matieres")
            .whereEqualTo("id" , matiereId)
            .get()
            .addOnSuccessListener { docs->
                for (doc in docs){
                    onSuccessCallBack(doc.toObject())
                }

            }
            .addOnFailureListener {
                onFailureCallBack(it)
            }


    }


    fun getCourses( matiereId : String ,  onSuccessCallBack: (List<Course>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){


        firebaseFirestore.collection("courses")
            .whereEqualTo("matiereId" , matiereId)
            .get()
            .addOnSuccessListener {
                onSuccessCallBack(it.toObjects(Course::class.java))
            }
            .addOnFailureListener(onFailureCallBack)


    }

    fun getCourses(   onSuccessCallBack: (List<Course>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){


        firebaseFirestore.collection("courses")
            .get()
            .addOnSuccessListener {
                onSuccessCallBack(it.toObjects(Course::class.java))
            }
            .addOnFailureListener(onFailureCallBack)


    }

    fun getCourses_Matieres(   onSuccessCallBack: (List<MatiereWithCourses>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        var courses = ArrayList<Course>()
        var courses_matieres = ArrayList<MatiereWithCourses>()
        var counter = 0

        firebaseFirestore.collection("courses")
            .get()
            .addOnSuccessListener {
                courses.addAll(it.toObjects(Course::class.java))

                courses.forEachIndexed { index, course ->
                    this.getMatieresById(
                        course.matiereId,
                        onSuccessCallBack = {matiere->

                            counter++

                            courses_matieres.add(MatiereWithCourses(courses = courses[index] , matiere = matiere))

                            if (counter == courses.size){
                                onSuccessCallBack(courses_matieres)
                            }

                        },
                        onFailureCallBack = onFailureCallBack
                    )
                }


            }
            .addOnFailureListener(onFailureCallBack)


    }

    fun getUser_Courses_Matieres(  userId : String ,  onSuccessCallBack: (List<MatiereWithCourses>) -> Unit , onFailureCallBack: (ex: Exception) -> Unit){

        var courses = ArrayList<Course>()
        var courses_matieres = ArrayList<MatiereWithCourses>()
        var counter = 0

        firebaseFirestore.collection("courses")
            .whereEqualTo("teacherId" , userId)
            .get()
            .addOnSuccessListener {
                courses.addAll(it.toObjects(Course::class.java))

                courses.forEachIndexed { index, course ->
                    this.getMatieresById(
                        course.matiereId,
                        onSuccessCallBack = {matiere->

                            counter++

                            courses_matieres.add(MatiereWithCourses(courses = courses[index] , matiere = matiere))

                            if (counter == courses.size){
                                onSuccessCallBack(courses_matieres)
                            }

                        },
                        onFailureCallBack = onFailureCallBack
                    )
                }


            }
            .addOnFailureListener(onFailureCallBack)


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

    fun teacherSignIn(email : String , password : String , onSuccessCallBack: ( Teacher ) -> Unit = {} , onFailureCallBack: (exp: Exception) -> Unit = {}){

        firebaseFirestore.collection("teachers")
            .whereEqualTo("email"    , email)
            .whereEqualTo("password" , password)
            .get()
            .addOnSuccessListener {accounts->

                if (accounts.isEmpty){
                    onFailureCallBack(Errors.userOrPasswordIncorrect())
                    return@addOnSuccessListener
                }

                var users = ArrayList<Teacher>()

                users.addAll(accounts.toObjects(Teacher::class.java))
                onSuccessCallBack(users[0])


            }
            .addOnFailureListener(onFailureCallBack)

    }

    fun studentSignIn(email : String , password : String , onSuccessCallBack: ( Student ) -> Unit = {} , onFailureCallBack: (exp: Exception) -> Unit = {}){

        firebaseFirestore.collection("students")
            .whereEqualTo("email"    , email)
            .whereEqualTo("password" , password)
            .get()
            .addOnSuccessListener {accounts->

                if (accounts.isEmpty){
                    onFailureCallBack(Errors.userOrPasswordIncorrect())
                    return@addOnSuccessListener
                }

                var users = ArrayList<Student>()

                users.addAll(accounts.toObjects(Student::class.java))
                onSuccessCallBack(users[0])


            }
            .addOnFailureListener(onFailureCallBack)

    }

}