public class BonusMilesService {

    // Метод для расчета количества бонусных миль
    public int calculate(int ticketPrice) {
        // Конверсионный коэффициент: 1 миля за каждые 20 рублей
        int milesPerUnit = 20;
        // Рассчитываем количество миль
        return ticketPrice / milesPerUnit;
    } }
