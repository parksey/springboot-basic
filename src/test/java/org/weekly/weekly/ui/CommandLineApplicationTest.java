package org.weekly.weekly.ui;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.weekly.weekly.ui.reader.CommandReader;
import org.weekly.weekly.ui.reader.ScannerWrap;
import org.weekly.weekly.ui.writer.CommandWriter;
import org.weekly.weekly.util.VoucherMenu;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandLineApplicationTest {
    private static CommandLineApplication commandLineApplication;

    @BeforeAll
    static void setCommandLineApplication() {
       commandLineApplication = new CommandLineApplication(new ScannerWrap(), new CommandWriter());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "EH1o", "Ex it", "Eapx", "test", "Ex1t", "1234"})
    void 사용자_메뉴판_통합테스트_입력시_예외발생(String userInput) {
        assertThatThrownBy(()->commandLineApplication.readMenu(userInput))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"exit", "Exit", "eXit", "List", "list", " Create ", "Create ", "create "})
    void 사용자_메뉴판_통합테스트_정상입력(String userInput) {
        // Given
        List<VoucherMenu> maps = List.of(VoucherMenu.values());

        // when
        boolean isContain = maps.contains(commandLineApplication.readMenu(userInput));

        // then
        assertThat(isContain).isTrue();
    }
}
