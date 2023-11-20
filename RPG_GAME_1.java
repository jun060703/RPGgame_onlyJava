package post;

import java.util.Random;
import java.util.Scanner;

public class RPG_GAME_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerHealth = 100;
        int playerLevel = 1;
        int playerExperience = 0;
        int playerDamage = 10;
        int playerCoins = 0;
        int potions = 3;
        int cityCooldown = 0; // 도시로 이동 쿨다운 카운터

        System.out.println("당신의 레벨은 " + playerLevel + "입니다. 모험을 시작합니다.");

        while (playerHealth > 0) {
            System.out.println("\n당신의 상태: 체력 " + playerHealth + " | 레벨 " + playerLevel + " | 경험치 " + playerExperience + " | 돈 " + playerCoins + " | 물약 " + potions + "개");
            System.out.println("어디로 가시겠습니까? (현재체력 " + playerHealth + ")");
            System.out.println("1. 도시로 이동");
            System.out.println("2. 동굴로 이동 (몬스터를 만나게 됨)");
            System.out.println("3. 무기상점으로 이동");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // 도시로 이동 시 쿨다운 적용
                if (cityCooldown > 0) {
                    System.out.println("도시로 연속 이동할 수 없습니다. 쿨다운 중입니다."+"쿨다운("+cityCooldown+")");
                } else {
                    // 도시 이벤트
                    int event = random.nextInt(3);
                    if (event == 0) {
                        System.out.println("도시에서 휴식하며 체력을 회복합니다. (+30 체력) (현재체력 " + playerHealth + ")");
                        playerHealth += 30;
                    } else if (event == 1) {
                    	 System.out.println("상인을 만났습니다. 물약을 구매합니다. (-10 돈)");
                         if (playerCoins >= 10) {
                             playerCoins -= 10;
                             potions++;
                             System.out.println("물약을 구매했습니다. (포션 개수 " + potions + ")");
                         } else {
                             System.out.println("돈이 부족하여 물약을 구매할 수 없습니다.");
                         }
                    } else {
                        System.out.println("도시에서 아무 일도 일어나지 않았습니다.");
                    }
                    
                    // 도시로 이동 쿨다운 설정
                    cityCooldown = 2;
                }
            } else if (choice == 2) {
                // 동굴 이벤트
                int enemyHealth = random.nextInt(playerLevel * 20) + 30;
                System.out.println("어둡고 무서운 동굴에 들어갔습니다. 적이 나타났습니다. (체력: " + enemyHealth + ")");

                while (enemyHealth > 0) {
                    System.out.println("\n무엇을 하시겠습니까? (현재체력 " + playerHealth + ")");
                    System.out.println("1. 공격");
                    System.out.println("2. 물약 사용 (포션 개수 " + potions + ")");
                    int combatChoice = scanner.nextInt();

                    if (combatChoice == 1) {
                        int damageDealt = random.nextInt(playerDamage) + 1;
                        int damageTaken = random.nextInt(10) + 1;

                        System.out.println("적에게 " + damageDealt + "의 피해를 입혔습니다. (적의 현재체력 " + enemyHealth + ")");
                        System.out.println("적이 당신에게 " + damageTaken + "의 피해를 입혔습니다. (현재체력 " + playerHealth + ")");
                        enemyHealth -= damageDealt;
                        playerHealth -= damageTaken;
                    } else if (combatChoice == 2) {
                        if (potions > 0) {
                            System.out.println("물약을 사용하여 체력을 회복합니다. (+20체력) (포션 개수 " + potions + ")");
                            playerHealth += 20;
                            potions--;
                        } else {
                            System.out.println("물약이 부족합니다.");
                        }
                    }

                    if (playerHealth <= 0) {
                        System.out.println("당신은 패배했습니다. 게임 오버!");
                        break;
                    }else if (choice == 3) {
						System.out.println();
					}
                }

                if (enemyHealth <= 0) {
                    int experienceGained = random.nextInt(30) + 20;
                    int coinsGained = random.nextInt(50) + 10;
                    System.out.println("적을 처치하였습니다! 경험치 " + experienceGained + " 획득 | 돈 " + coinsGained + " 획득!");
                    playerExperience += experienceGained;
                    playerCoins += coinsGained;

                    // 레벨 업
                    if (playerExperience >= playerLevel * 100) {
                        playerLevel++;
                        playerDamage +=playerDamage += 5;
                        playerHealth+=60;
                        System.out.println("레벨 업! 레벨 " + playerLevel + " 달성!");
                    }
                }
            }
            if (cityCooldown > 0) {
                cityCooldown--;
            }
        }

        scanner.close();
    }
}

