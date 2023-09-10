// массив на 5 элементов. Заполняем массив
// int n = 5;
// int[] array = new int[n];
// for (int i = 0; i < n; i++)
//     array[i] = Convert.ToInt32(Console.ReadLine());
// Console.WriteLine("[" + string.Join(", ", array) + "]");// через ", " выводим все элементы
// Console.WriteLine(array[3]);
// как посчитать время работы программы если нам нужно найти определенный элемент из массива
// Big O Notation
// В чем сложность алгоритма? -> O notation. O(1) - сколько действий выполняем до получения конечного результата.
// Словари. Телефон-контакты. Необходимо что-то найти - за 1млсек, несмотря на количество данных
// Сколько операций следует выполнить, чтоб найти сумму всех чисел в массиве? Ответ - O(n). n - кол-во элементов в массиве
// Быстрая сортировка - [1, 2, 3, 4, 5] - O(n * log n)
// ((5 + 1)/2) * 5 - O(1) - сумма арифметической прогрессии
// n < n * log(n) + 1 при использовании сложных алгоритмов мы можем использовать большее кол-во затрачиваемого времени на выполнение какой-дибо программы 
// int summa = 0;
// for (int i = 0; i < n; i++)
//     summa += array[i];
// Console.WriteLine(summa);

// задание - таблица умножение. Пользователь вводит - 5. Значит таблица из 5 чисел вертикально и горизонтально и перемножение
// int n = Convert.ToInt32(Console.ReadLine());
// for (int i = 1; i < n; i++)
// {
//     for (int j = 1; j <= n; j++)
//     {
//         Console.Write(i*j);
//         Console.Write("\t"); // Более красивый вывод
//     }
// Console.WriteLine();
// }
// время работы программы - O(n^2)
// чтобы автоматизировать код, нужно использовать матрицу

int n = Convert.ToInt32(Console.ReadLine());
int[, ] matrix = new int[n, n];
// for (int i = 1; i < n; i++)
// {
//     for (int j = 1; j <= n; j++)
//     {
//         if (i + j <= n)
//         {
//             matrix[i ,j] = i * j;
//             matrix[j, i] = i * j;
//         }
//         Console.Write(i*j);
//         Console.Write("\t"); // Более красивый вывод
//     }
// Console.WriteLine();
// }
// Сложность работы программы - O(n^2)

// for (int i = 1; i <= n/2; i++)
// {
//     for (int j = 1; j <= n/2; j++)
//     {
//         Console.Write(matrix[i, j]);
//         Console.Write("\t"); // Более красивый вывод
//     }
// Console.WriteLine();
// }
// Сложность работы программы O(n^2 / 2)

// for (int i = 0; i < n; i++)
// {
//     for (int j = 1; j < n; j++)
//     {
//         matrix[i ,j] = (i+1) * (j+1);
//         matrix[j, i] = (i+1) * (j+1);
//     }
// Console.WriteLine();
// }
// Сложность работы программы - O(n^2)

// for (int i = 0; i < n; i++)
// {
//     for (int j = 0; j < n; j++)
//     {
//         Console.Write(matrix[i, j]);
//         Console.Write("\t"); // Более красивый вывод
//     }
// Console.WriteLine();
// }
// сложность алгоритма - O(n^2)