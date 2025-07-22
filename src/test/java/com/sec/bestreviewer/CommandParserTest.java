package com.sec.bestreviewer;

import com.sec.bestreviewer.condition.TokenGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
    @Test
    void 옵션이_없는_CMD를_잘파싱하는지_확인() {
        String line = "ADD, , ,,08951033,QDJPTOJ KIM,CL3,010-3240-5443,19800308";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("ADD", tokens.getType());

        String[] options = {};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"08951033", "QDJPTOJ KIM", "CL3", "010-3240-5443", "19800308"};
        //assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    void 옵션이_있는_CMD를_잘파싱하는지_확인() {
        String line = "SCH,-p, , ,phoneNum,010-2742-2901";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);


        assertEquals("SCH", tokens.getType());

        String[] options = {"-p"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"phoneNum", "010-2742-2901"};
        //assertArrayEquals(params, tokens.getParams().toArray());
    }

    @Test
    @DisplayName("MOD명령어 확인")
    void MOD명령어를_잘파싱하는지_확인() {
        String line = "MOD,-p, , ,name,YUJIN KIM,phoneNum,010-0970-0055";

        CommandParser commandParser = new CommandParser();
        TokenGroup tokens = commandParser.parse(line);

        assertEquals("MOD", tokens.getType());

        String[] options = {"-p"};
        assertArrayEquals(options, tokens.getOptions().toArray());

        String[] params = {"name", "YUJIN KIM", "phoneNum", "010-0970-0055"};
        //assertArrayEquals(params, tokens.getParams().toArray());
    }
}
