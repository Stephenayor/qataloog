
//
//import com.squareup.moshi.Json
//
//// To parse the JSON, install Klaxon and do:
////
////   val welcome4 = Welcome4.fromJson(jsonString)
//
//
//import com.beust.klaxon.*
//import com.bumptech.glide.load.model.ByteArrayLoader
//import retrofit2.Converter
//
//private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
//    this.converter(object: Converter {
//        @Suppress("UNCHECKED_CAST")
//        override fun toJson(value: Any)        = toJson(value as T)
//        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
//        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
//    })
//
//private val klaxon = Klaxon()
//    .convert(Title::class,      { Title.fromValue(it.string!!) },      { "\"${it.value}\"" })
//    .convert(Language::class,   { Language.fromValue(it.string!!) },   { "\"${it.value}\"" })
//    .convert(Interest::class,   { Interest.fromValue(it.string!!) },   { "\"${it.value}\"" })
//    .convert(Occupation::class, { Occupation.fromValue(it.string!!) }, { "\"${it.value}\"" })
//    .convert(Role::class,       { Role.fromValue(it.string!!) },       { "\"${it.value}\"" })
//
//data class Welcome4 (
//    val data: List<Datum>,
//    val message: String
//) {
//    public fun toJson() = klaxon.toJsonString(this)
//
//    companion object {
//        public fun fromJson(json: String) = klaxon.parse<Welcome4>(json)
//    }
//}
//
//data class Datum (
//    val id: Long,
//
//    @Json(name = "user_id")
//    val userID: Long,
//
//    val name: String,
//    val description: String,
//
//    @Json(name = "deleted_at")
//    val deletedAt: Any? = null,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String,
//
//    val icon: String,
//    val books: List<Book>,
//    val users: List<User>,
//    val invites: List<Any?>,
//
//    @Json(name = "get_pending_invite")
//    val getPendingInvite: List<GetPendingInvite>,
//
//    val pivot: Pivot? = null
//)
//
//data class Book (
//    val id: Long,
//
//    @Json(name = "book_id")
//    val bookID: String,
//
//    val author: String,
//    val title: String,
//    val description: String,
//    val isn: Any? = null,
//
//    @Json(name = "ISBN")
//    val isbn: String? = null,
//
//    val cover: String,
//
//    @Json(name = "download_link")
//    val downloadLink: String,
//
//    val cost: Long,
//
//    @Json(name = "category_id")
//    val categoryID: Long,
//
//    @Json(name = "sub_category_id")
//    val subCategoryID: Long? = null,
//
//    @Json(name = "user_id")
//    val userID: Long,
//
//    val status: Long,
//
//    @Json(name = "deleted_at")
//    val deletedAt: Any? = null,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String,
//
//    @Json(name = "visibility_status")
//    val visibilityStatus: Long,
//
//    @Json(name = "delete_date")
//    val deleteDate: Any? = null,
//
//    val language: Language,
//    val isFree: String,
//
//    @Json(name = "pub_year")
//    val pubYear: String? = null,
//
//    val newpublisher: Any? = null,
//    val publisher: String? = null,
//    val readtime: Long,
//    val amountearned: Long,
//    val pivot: Pivot,
//    val category: Category,
//    val user: User
//)
//
//data class Category (
//    val id: Long,
//
//    @Json(name = "user_id")
//    val userID: Any? = null,
//
//    val title: Title,
//    val rate: Double,
//    val description: Any? = null,
//    val icon: Any? = null,
//
//    @Json(name = "deleted_at")
//    val deletedAt: Any? = null,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String,
//
//    @Json(name = "sub_categories")
//    val subCategories: List<SubCategory>
//)
//
//data class SubCategory (
//    val id: Long,
//    val name: String,
//
//    @Json(name = "category_id")
//    val categoryID: Long,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String
//)
//
//enum class Title(val value: String) {
//    HigherEducation("Higher Education"),
//    Tvet("TVET");
//
//    companion object {
//        public fun fromValue(value: String): Title = when (value) {
//            "Higher Education" -> HigherEducation
//            "TVET"             -> Tvet
//            else               -> throw IllegalArgumentException()
//        }
//    }
//}
//
//enum class Language(val value: String) {
//    English("english"),
//    French("french");
//
//    companion object {
//        public fun fromValue(value: String): Language = when (value) {
//            "english" -> English
//            "french"  -> French
//            else      -> throw IllegalArgumentException()
//        }
//    }
//}
//
//data class Pivot (
//    @Json(name = "library_id")
//    val libraryID: Long,
//
//    @Json(name = "book_id")
//    val bookID: Long? = null,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String,
//
//    @Json(name = "user_id")
//    val userID: Long? = null
//)
//
//data class User (
//    val id: Long,
//    val uuid: String,
//    val name: String,
//    val email: String,
//    val occupation: Occupation? = null,
//    val interests: List<Interest>,
//
//    @Json(name = "profile_pic")
//    val profilePic: String? = null,
//
//    @Json(name = "subscription_status")
//    val subscriptionStatus: Boolean,
//
//    @Json(name = "has_used_trial")
//    val hasUsedTrial: Boolean,
//
//    @Json(name = "on_trial")
//    val onTrial: Boolean,
//
//    val roles: List<Role>
//)
//
//enum class Interest(val value: String) {
//    Coding("coding"),
//    Eating("eating"),
//    Geology("geology"),
//    Mathematics("Mathematics"),
//    Reading("reading"),
//    Sport("Sport");
//
//    companion object {
//        public fun fromValue(value: String): Interest = when (value) {
//            "coding"      -> Coding
//            "eating"      -> Eating
//            "geology"     -> Geology
//            "Mathematics" -> Mathematics
//            "reading"     -> Reading
//            "Sport"       -> Sport
//            else          -> throw IllegalArgumentException()
//        }
//    }
//}
//
//enum class Occupation(val value: String) {
//    Lecturer("Lecturer"),
//    Librarian("Librarian");
//
//    companion object {
//        public fun fromValue(value: String): Occupation = when (value) {
//            "Lecturer"  -> Lecturer
//            "Librarian" -> Librarian
//            else        -> throw IllegalArgumentException()
//        }
//    }
//}
//
////enum class Role(val value: String) {
////    Administrator("Administrator"),
////    User("User");
////
////    companion object {
////        public fun fromValue(value: String): Role = when (value) {
////            "Administrator" -> Administrator
////            "User"          -> User
////            else            -> throw IllegalArgumentException()
////        }
////    }
////}
//
//data class GetPendingInvite (
//    val id: Long,
//
//    @Json(name = "library_id")
//    val libraryID: Long,
//
//    @Json(name = "user_id")
//    val userID: String,
//
//    val email: String,
//
//    @Json(name = "accept_token")
//    val acceptToken: String,
//
//    @Json(name = "deny_token")
//    val denyToken: String,
//
//    @Json(name = "created_at")
//    val createdAt: String,
//
//    @Json(name = "updated_at")
//    val updatedAt: String,
//
//    val status: Any? = null
//)
