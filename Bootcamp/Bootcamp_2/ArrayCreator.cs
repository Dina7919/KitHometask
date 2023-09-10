
/// <summary>
/// Это класс, отвечающий за создание массива
/// </semmary>
public static class ArrayCreator
{
    /// <summary>
    /// Метод создания массива
    /// </summary>
    /// <param name="n">Количество элементов</param>
    /// <returns>новый массив</returns> 
    public static int[] Create(this int n)
    {
        return new int[n];
    }

    /// <summary>
    /// Лепит массив в строку
    /// </summary>
    /// <param name="array">Массив</param>
    /// <returns>Строку с представлением массива</returns> 
    public static string ConvertToStringAndPrintToTerminal (this int[] array)
    {
        string str = $"[{String.Join(',', array)}]";
        System.Console.WriteLine(str);
        return str;
    }

    /// <summary>
    /// Заполняет массив
    /// </summary>
    /// <param name="array">Массив</param>
    /// <param name="min">Нижняя граница диапазона генератора случайных чисел</param>
    /// <param name="max">Верхняя граница диапазона генератора случайных чисел</param>
    public static int[] Fill(this int[] array, int min = 0, int max = 10, int seed = 0)
    {
        Random random = seed == 0 ? new Random() : new Random(seed);
        return array.Select(item => random.Next(min, max)).ToArray();
    }

}