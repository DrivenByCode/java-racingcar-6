package racingcar.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import racingcar.constant.ErrorMessage;
import racingcar.constant.ServiceMessage;
import racingcar.input.count.CountReader;
import racingcar.input.count.CountValidator;
import racingcar.input.name.NameReader;
import racingcar.input.name.NameValidator;
import racingcar.output.DriverProgress;
import racingcar.output.ServicePrinter;
import racingcar.service.winner.WinnerSelector;

public class RacingGame {
    public void startGame() {
        ServicePrinter servicePrinter = new ServicePrinter();
        servicePrinter.printStartMessage();

        // 예외 처리
        String[] names = NameReader.read();
        // 횟수 입력 문구 출력
        servicePrinter.printInputMessage();

        // 횟수 입력 받기
        int count = CountReader.read();

        servicePrinter.printOutputMessage();

        int[] countsByName = DriverProgress.race(names, count);
        // 최대 전진 횟수 찾기
        int maxCount = Arrays.stream(countsByName).max().orElse(-1);

        // 최대 전진 횟수와 일치하는 모든 운전자의 이름 찾기
        List<String> winners = WinnerSelector.getWinners(maxCount, names, countsByName);

        servicePrinter.printWinnerMessage(winners);
    }
}
