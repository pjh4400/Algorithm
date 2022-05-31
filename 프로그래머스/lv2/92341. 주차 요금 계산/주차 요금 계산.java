import java.util.*;
import java.time.LocalTime;

class Solution {
    int defaultMinute, defaultFee, unitMinute, unitFee;
    Map<String, LocalTime> inMap = new HashMap<>();
    Map<String, Integer> parkingMinutesMap = new HashMap<>(); // 차량별 누적 주차 시간
    Map<String, Integer> feeMap = new TreeMap<>(); // 요금
    
    // 주차 요금, 입/출차 내역 문자열 배열,  차량 번호가 작은 자동차부터
    public int[] solution(int[] fees, String[] records) {
        defaultMinute = fees[0];
        defaultFee = fees[1];
        unitMinute = fees[2];
        unitFee = fees[3];
        for (String r : records){
            String[] record = r.split(" ");
            String[] times = record[0].split(":");
            String carNumber = record[1];
            LocalTime time = LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
            if(!inMap.containsKey(carNumber)){
                inMap.put(carNumber, time);
                continue;
            }
            LocalTime inTime = inMap.get(carNumber);
            LocalTime parkingTime = time.minusHours(inTime.getHour()).minusMinutes(inTime.getMinute());
            int parkingMinutes = parkingTime.getHour()*60 + parkingTime.getMinute();
            parkingMinutesMap.put(carNumber, parkingMinutesMap.getOrDefault(carNumber, 0) + parkingMinutes);
            inMap.remove(carNumber);
        }
        
        
        // 출차된 내역이 없으먄 23:59에 출차된 것
        LocalTime outTime = LocalTime.of(23,59);
        for(String carNumber : inMap.keySet()){
            LocalTime inTime = inMap.get(carNumber);
            LocalTime parkingTime = outTime.minusHours(inTime.getHour()).minusMinutes(inTime.getMinute());
            int parkingMinutes = parkingTime.getHour()*60 + parkingTime.getMinute();
            parkingMinutesMap.put(carNumber, parkingMinutesMap.getOrDefault(carNumber, 0) + parkingMinutes);
        }
        
        // 누적 시간 기본 시간이하 : 기본 요금
        // 초과 : 기본 요금 + 초과한 단위 시간마다 단위 요금
        // 나누어 떨어지지 않으면, 올림
        for(String carNumber : parkingMinutesMap.keySet()){
            int fee = defaultFee;
            int overMinutes = parkingMinutesMap.get(carNumber) - defaultMinute;
            if(overMinutes > 0){
                fee += Math.ceil((double) overMinutes / unitMinute) * unitFee;
            }
            feeMap.put(carNumber, fee);
        }
        
        int[] answer = new int[feeMap.size()];
        int i = 0;
        for(String carNumber : feeMap.keySet()){
            answer[i] = feeMap.get(carNumber);
            i++;
        }
        return answer;
    }

}