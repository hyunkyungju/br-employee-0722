package com.sec.bestreviewer.mod;

import com.sec.bestreviewer.EmployeeManagement;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class EmploymentManagementModTest {

    private Path inputFile;
    private Path outputFile;
    private String[] args;

    @BeforeEach
    void setUp() throws IOException {

        String input = String.join("\n", List.of(
                "ADD, , , ,91351446,LIM PNQN,CL3,010-1234-5678,19700122,ADV",
                "ADD, , , ,06134213,JIN LIN,CL3,010-0320-1235,19821022,PRO",
                "ADD, , , ,09143542,HAEIN JUNG,CL3,010-0210-5423,19840212,EX",
                "ADD, , , ,10142536,MICHAEL JACKSON,CL3,010-0410-4532,19740730,ADV",
                "ADD, , , ,11231432,AMANDI GUPTA,CL3,010-0650-1362,19880228,EX",
                "ADD, , , ,18350301,KIM EOE,CL4,010-0840-4988,19810408,EX",
                "ADD, , , ,07843022,SEO KFI,CL3,010-0830-6716,19810408,PRO",
                "ADD, , , ,12345678,YUJIN KIM,CL2,010-0970-0055,19900101,PRO",
                "ADD, , , ,23456789,YUJIN LEE,CL2,010-0970-0055,19920101,PRO",
                "ADD, , , ,34567890,MIN KIM,CL2,010-0970-0066,19900106,PRO",
                "ADD, , , ,45678901,LEE YUJIN,CL1,010-1111-2222,19901225,EX",
                "ADD, , , ,56789012,PARK KIM,CL1,010-1111-2223,19901206,PRO",
                "ADD, , , ,67890123,YUJIN KIM,CL1,010-1111-2224,19900106,ADV",
                "ADD, , , ,78901234,KIM YUJIN,CL2,010-9999-8888,19880909,EX",
                "ADD, , , ,89012345,YUJIN PARK,CL3,010-9999-8888,19871007,PRO",
                "ADD, , , ,90123456,UNKNOWN NAME,CL1,010-0000-0000,19990101,ADV")) + "\n";

        inputFile = Files.createTempFile("test-data", ".txt");
        outputFile = Files.createTempFile("test-output", ".txt");
        args = new String[]{
                inputFile.toFile().getAbsolutePath(),
                outputFile.toFile().getAbsolutePath()
        };

        Files.writeString(inputFile, input);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(inputFile);
        Files.deleteIfExists(outputFile);
    }

    @DisplayName("[MOD] 사용자 이름 변경")
    @Test
    void modChangeName() throws IOException {
        String command = "MOD,-p, , ,name,YUJIN KIM,name,YUJIN LEE\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] CL로 검색 & 핸드폰 번호 변경")
    @Test
    void modChangePhoneNumberByCL() throws IOException {
        String command = "MOD, , , ,cl,CL3,phoneNum,010-0970-0055\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 핸드폰 번호 검색 & 생년월일 변경")
    @Test
    void modChangeBirthdayByPhoneNumber() throws IOException {
        String command = "MOD,-p, , ,phoneNum,010-0970-0055,birthday,20001225\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 성(Last Name)으로 전화번호 변경")
    @Test
    void modChangePhoneNumberByLastName() throws IOException {
        String command = "MOD,-p,-l, ,name,KIM,phoneNum,010-0970-0055\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 전화번호 뒷자리로 certi 변경")
    @Test
    void modChangeCertiByPhoneNumberTail() throws IOException {
        String command = "MOD, ,-l, ,phoneNum,0055,certi,EX\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 전화번호 중간으로 certi 변경")
    @Test
    void modChangeCertiByPhoneNumberMiddle() throws IOException {
        String command = "MOD, ,-m, ,phoneNum,0970,certi,EX\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 생년월일 연도로 이름 변경")
    @Test
    void modChangeNameByBirthYear() throws IOException {
        String command = "MOD,-p,-y, ,birthday,1990,name,YUJIN LEE\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] and 옵션: CL2 + 생년월 01로 이름 변경")
    @Test
    void modChangeNameByCLAndBirthMonth() throws IOException {
        String command = "MOD,-p, , ,cl,CL2,-a,-m, ,birthday,01,name,YUJIN LEE\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] or 옵션: 생년일 06 또는 certi PRO로 생년월일 변경")
    @Test
    void modChangeBirthdayByBirthDayOrCerti() throws IOException {
        String command = "MOD, ,-d, ,birthday,06,-o, , ,certi,PRO,birthday,19901225\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 조건 불일치(NONE)")
    @Test
    void modNone() throws IOException {
        String command = "MOD,-p, , ,employeeNum,99999999,name,NEW NAME\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }

    @DisplayName("[MOD] 사원번호 수정 불가")
    @Test
    void modChangeEmployeeNumNotAllowed() throws IOException {
        String command = "MOD, , , ,employeeNum,91351446,employeeNum,00000000\n";
        Files.writeString(inputFile, command, StandardOpenOption.APPEND);

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);

        String output = Files.readString(outputFile);
        Approvals.verify(output);
    }
}
