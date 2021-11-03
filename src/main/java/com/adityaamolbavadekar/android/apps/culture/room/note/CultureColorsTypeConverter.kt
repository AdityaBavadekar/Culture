class Converter { 

@TypeConverter 
fun fromColor(priority: Priority): String { 
 return .name
 } 

@TypeConverter fun toColor(priority: String): CultureColors {
 return Priority.valueOf(priority) 
 } 

}
