package com.example.daracademy.model.sealedClasses.userType

import com.example.daracademy.model.objects.userType.user_type

sealed class UserType(val type:String){

    class AnonymousUser : UserType(type = user_type.anonymous_user)
    class TeacherUser   : UserType(type = user_type.teacher_user)
    class StudentUser   : UserType(type = user_type.student_user)

}
