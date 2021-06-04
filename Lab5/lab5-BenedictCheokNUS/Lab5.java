/** 
 * CS2030 2020/21 ST1
 * Lab 5: Maybe
 * File Name: Lab5.java
 * Description: This class Lab5 has the method getGrade, which takes in
 * the student name, module, assessment,
 * and the student-module-assessment-grade mappings
 * and return the grade of the student for the specified module
 * for the specified assessment.
 *
 * @author Benedict Cheok Wei En (B03), A0199433U
 */

import cs2030.fp.Maybe;
import cs2030.fp.Transformer;
import java.util.Map;

class Lab5 {

  /**OLD METHOD*/
  /*
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> db) {
    Map<String, Map<String, String>> std = db.get(student);
    if (std == null) {
      return "No such entry";
    } else {
      Map<String, String> mod = std.get(module);
      if (mod == null) {
        return "No such entry";
      } else {
        String grade = mod.get(assessment);
        if (grade == null) {
          return "No such entry";
        }
        return grade;
      }
    }
  }
  */
  /**NEW METHOD IMPLEMENTATION*/
  ///*
  //Return String
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {

    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> getModule = 
        new Transformer<>() {
          //Transformer takes in a Map<String, Map<String, String>> --> module mappings
          //Transformer outputs a Maybe<Map<String, String>> --> assessment mappings of the module
          public Maybe<Map<String, String>> transform(Map<String, Map<String, String>> modM) { 
            //Inputs: Map<String Module, Map<String Assessment, String Grade>> --> module mappings
            return Maybe.of(modM.get(module));
            //returns Maybe<Map<String Assessment, String Grade>> --> 
            //  assessment mappings of the module
            //map.get(module) would return null if module not found
            //OR return module details, which is the map of Assessments and Grade

          }
        };

    Transformer<Map<String, String>, Maybe<String>> getAssessment = new Transformer<>() {
      //Transformer takes in a Map<String, String> --> assessment mappings
      //Transformer outputs a Maybe<String> --> Grade     
      public Maybe<String> transform(Map<String, String> assM) {
        //Inputs: Map<String Assessment, String Grade> --> assessment mappings
        return Maybe.of(assM.get(assessment)); 
        //returns Maybe<String Grade>
        //map.get(assessment) would return null if assessment not found
        //OR return grades of that assessment, which is a string.
      }
    };

    //THOUGHT PROCESS
    //getAssessment gets the Maybe<Grade> of that assessment
    //flatMap(getModule).flatMap(getAssessment) --> 
    //  flatMap(getModule).flatMap(Maybe<Grade> of that assessment)
    //  ^ so... it gets the Maybe<Grade> of that assessment of that module
    //Maybe.of(map.get(student)) creates Maybe<student>
    //where student is Map<Module, Map<Assessment, Grade>>
    //Maybe.of(map.get(student)).flatMap(getModule)... --> 
    //  Maybe<Student>.flatMap(Maybe<Grade> of that assessment of that module))
    //  which returns Maybe<Grade> of that assessment of that module of that student
    //Maybe.of(map.get(student)).flatMap(getModule).......orElse("No such entry") -->
    //  Maybe<Grade>.orElse("No such entry")
    //  which returns Grade if Grade is not null, else return "No such entry"
    
    return Maybe.of(map.get(student)).flatMap(getModule).flatMap(getAssessment)
      .orElse("No such entry");
  }
  //*/

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
            "Steve", Map.of(
                "CS2030", Map.of(
                        "lab1", "A",
                        "lab2", "A-",
                        "lab3", "A+",
                        "lab4", "B",
                        "pe1", "C"),
                "CS2040", Map.of(
                        "lab1", "A",
                        "lab2", "A+",
                        "lab3", "A+",
                        "lab4", "A",
                        "midterm", "A+")),
            "Tony", Map.of(
                "CS2030", Map.of(
                    "lab1", "C",
                    "lab2", "C",
                    "lab3", "B-",
                    "lab4", "B+",
                    "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030", "lab1", students));  //Prints: A
    System.out.println(getGrade("Steve", "CS2030", "lab2", students));  //Prints: A-
    System.out.println(getGrade("Steve", "CS2040", "lab3", students));  //Prints: A+
    System.out.println(getGrade("Steve", "CS2040", "lab4", students));  //Prints: A
    System.out.println(getGrade("Tony", "CS2030", "lab1", students));   //Prints: C
    System.out.println(getGrade("Tony", "CS2030", "midterm", students)); //Prints: No such entry
    System.out.println(getGrade("Tony", "CS2040", "lab4", students));   //Prints: No such entry
    System.out.println(getGrade("Bruce", "CS2040", "lab4", students));  //Prints: No such entry
  }
}
