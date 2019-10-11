# Programmers.Level3_Java_NightShiftIndices

## 프로그래머스 > 코딩테스트 > 연습문제 > 야근지수

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/12927

input으로 ~~야근까지~~ 퇴근까지 남은시간 `N`, 각 일에대한 작업량 `int[] works`가 들어온다. `1`시간 당 작업을 `1`씩 해결가능하다. 야근지수를 계산하여 return하는 문제.

* 야근 지수
> 야근이 시작될 때 남은 일에 대하여 각각의 남은 양의 제곱을 합한 값
> ex) [1, 2, 2], 야근 지수: 1 + 4 + 4 = 9

### 2. 풀이

첫째로 `N` 이 남은 일양의 총합보다 크다면 야근을 할 필요가 없으므로 `0`을 return한다. 

```java

if (Arrays.stream(works).sum() <= n)
    		return 0;

```

남은 일들을 양에 따라 정렬하였다. 야근지수의 값을 최소로 하기 위해서는 가장 많이 남을 일에 대하여 먼저 처리하여야 한다. 가장 오래남은 일의 양을 `max`로 찾고 다음으로 많이 남은 양의 값을 `nextMax`로 저장한다. 그리고 `max`의 양을 가진 일의 갯수를 세어 `maxCount`에 저장한다. `diff`를 큰 일의 양에서 두번째로 큰 일의 양의 차이 `max - nextMax`라고 할때, 만약 `N`이 `diff * maxCount` 보다 크거나 같다면 `maxCount`개의 일을 `diff`만큼 뺄 수 있으며 그 만큼 `N`값도 빼준다. `diff * maxCount`보다 크지 않다면 `N`과 `maxCount`를 비교한다. `N`이 `maxCount`보다 크거나 같다면 `maxCount`개의 일에 대해 양을 1씩 줄이고 `N`은 `maxCount`만큼 줄인다. 마지막으로 `N`이 `maxCount`보다 작다면 `N`개의 일의 양을 1씩 줄이고 남은 일들에 대해 제곱한 합을 return해주면 된다.

```java

Arrays.sort(works);

int diff;
int maxCount;

while (true) {
int idx = works.length - 1;
int max = works[idx];
int nextMax = Arrays.stream(works).filter(i -> i < max).max().orElse(-1);

if (nextMax == -1) {
  maxCount = works.length;
  int sub = 0;
while (n >= maxCount) {
  n -= maxCount;
  sub++;
}
for (int i = 0; i < maxCount; i++) {
  works[idx - i] -= sub;
}
for (int i = 0; i < n; i++) {
  works[idx - i]--;
}

  break;
} else {
  diff = max - nextMax;
  maxCount = (int) Arrays.stream(works).filter(i -> i == max).count();
  if (diff * maxCount <= n) {
    n -= maxCount * diff;
    for (int i = 0; i < maxCount; i++) {
      works[idx - i] -= diff;
    }
  } else {
    int sub = 0;
    while (n >= maxCount) {
      n -= maxCount;
      sub++;
    }
    for (int i = 0; i < maxCount; i++) {
      works[idx - i] -= sub;
    }
    for (int i = 0; i < n; i++) {
      works[idx - i]--;
    }

    break;
  }
}
}


for (int i = 0; i < works.length; i++) {
  answer += works[i]*works[i];
}

return answer;

```

### 3. 유사한 문제

정리: https://github.com/ckddn9496/Programmers.2018_KAKAOBLINDRECRUITMENT_Java_MuckbangLive

문제: https://programmers.co.kr/learn/courses/30/lessons/42891

2018 카카오 블라인트 테스트에 나온 무지의 먹방라이브와 비슷했다. `N`번의 반복을 하지않고 차이만큼 한번에 빼버리는 방법을 응용하여 문제를 풀었다.
