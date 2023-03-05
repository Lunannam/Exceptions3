package Exceptions;


import Exceptions.*;





/* Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки

дата_рождения - строка формата dd.mm.yyyy

номер_телефона - целое беззнаковое число без форматирования

пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, 
вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать 
встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение 
с информацией, что именно неверно.
Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку
 должны записаться полученные данные, вида <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть 
корректно обработано, пользователь должен увидеть стектрейс ошибки.
 * 
 */
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
	

	public class Main {
	    public static void main(String[] args) {
	        while (true) {
	

	            try {
	                String[] result = makeData(addUserInput());
	                checkName(result);
	                checkBirthdate(result);
	                checkPhone(result);
	                checkSex(result);
	                writeData(result);
	                break;
	

	            } catch (NumberFormatException e) {
	                System.out.println("Enter INCORRECT. Use numbers");
	                continue;

	            } catch (DateTimeParseException e) {
	                System.out.println("Date format incorrect. MUST BE dd.mm.yyyy");
	                continue;

	            } catch (InputsizeException e) {
	                System.out.println(
	                        "Incorrect data format. Re-enter data correctly");
	                continue;
	            } catch (IncorrectNameException e) {
	                System.out.println("Enter format: Фамилия, Имя, Отчество");
	                continue;
	            } catch (IncorrectSexException e) {
	                System.out.println("Inccorect enter. Enter f or m ");
	                continue;
	            }
	        }
	

	    }
	

	    public static String addUserInput() {
	        System.out.println(
	                "\n Hello");
	        System.out.println(
	                "Введите данные через ПРОБЕЛ в следующем формате:\n<Фамилия>,<Имя>,<Отчество> <Дата рождения в формате dd.mm.yyyy> <Номер телефона. Целое число> <Пол. f - жен. или m - мужской>");
	        String input = "";
	        Scanner scanner = new Scanner(System.in);
	        try{
	            input = scanner.nextLine();
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	            scanner.close();
	        }
	        
	

	        return input;
	    }
	
	    public static String[] makeData(String input) {
	        int dataLenght = 4;
	        String[] result = input.split(" ");
	        if (result.length < dataLenght) {
	            throw new InputsizeException();
	        }
	        return result;
	    }
	

	    public static String[] checkName(String[] result) {
	

	        int dataLenght = 3;
	        String[] name = result[0].strip().split(",");
	        if (name.length != dataLenght) {
	            throw new IncorrectNameException();
	        }
	        return name;
	    }
	

	    public static void checkBirthdate(String[] result) throws DateTimeParseException {
	

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
	        LocalDate ld = LocalDate.parse(result[1], formatter);
	

	    }
	

	    public static void checkPhone(String[] result) throws NumberFormatException {
	

	        Integer.parseInt(result[2]);
	    }
	

	    public static void checkSex(String[] result) {
	        if (!result[3].equals("ж") && !result[3].equals("м")) {
	            throw new IncorrectSexException();
	        }
	    }
	

	    public static String fromArrayToString(String[] result) {
	        StringBuilder sb = new StringBuilder();
	        for (String s : result) {
	            sb.append(s + " ");
	        }
	        return sb.toString();
	    }
	

	    public static void writeData(String[] result) {
	        String fileName = checkName(result)[0];
	        String forWrite = fromArrayToString(result);
	        try (FileWriter writer = new FileWriter(String.format("Exceptions/%s.txt", fileName), true)) {
	            writer.append(forWrite);
	            writer.append("\n");
	            System.out.println("\n Success record add:)");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    }

