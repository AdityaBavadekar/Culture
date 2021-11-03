class Converter { 

@TypeConverter 
 fun fromColor(cultureColours : CultureColours) : String { 
  return cultureColours.name
 } 

@TypeConverter 
 fun toColor(colour: String): CultureColors {
  return CultureColours.valueOf(colour) 
 } 

}
