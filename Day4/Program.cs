using System;

var input = System.IO.File.ReadAllLines(path: "../Day4/input.txt");

char[,] grid = ConvertToGrid(input);

string word = "XMAS";
int count = CountWordOccurrences(grid, word);

Console.WriteLine($"XMAS: {count}");

char[,] grid2 = ConvertToGrid(input);

int count2 = CountXMASPatterns(grid2);

Console.WriteLine($"X-MAS: {count2}");

static char[,] ConvertToGrid(string[] input)
{
    int rows = input.Length;
    int cols = 0;

    foreach (string line in input)
        cols = Math.Max(cols, line.Length);

    char[,] grid = new char[rows, cols];

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            grid[i, j] = j < input[i].Length ? input[i][j] : '.';
        }
    }

    return grid;
}


static int CountWordOccurrences(char[,] grid, string word)
{
    int rows = grid.GetLength(0);
    int cols = grid.GetLength(1);
    int count = 0;

    int[] dx = { 0, 0, 1, -1, 1, 1, -1, -1 };
    int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            for (int dir = 0; dir < 8; dir++)
            {
                if (CheckWord(grid, word, i, j, dx[dir], dy[dir]))
                    count++;
            }
        }
    }

    return count;
}

static bool CheckWord(char[,] grid, string word, int x, int y, int dx, int dy)
{
    int rows = grid.GetLength(0);
    int cols = grid.GetLength(1);
    int len = word.Length;

    for (int k = 0; k < len; k++)
    {
        int nx = x + k * dx;
        int ny = y + k * dy;

        if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
            return false;

        if (grid[nx, ny] != word[k])
            return false;
    }

    return true;
}

static int CountXMASPatterns(char[,] grid)
{
    int rows = grid.GetLength(0);
    int cols = grid.GetLength(1);
    int count = 0;

    for (int i = 1; i < rows - 1; i++)
    {
        for (int j = 1; j < cols - 1; j++)
        {
            if (grid[i, j] == 'A')
            {
                if (IsXMASPattern(grid, i, j))
                {
                    count++;
                }
            }
        }
    }

    return count;
}

static bool IsXMASPattern(char[,] grid, int x, int y)
{
    string[] validSequences = { "MAS", "SAM" };

    string diagonal1 = $"{grid[x - 1, y - 1]}{grid[x, y]}{grid[x + 1, y + 1]}";

    string diagonal2 = $"{grid[x - 1, y + 1]}{grid[x, y]}{grid[x + 1, y - 1]}";

    bool isDiagonal1Valid = validSequences.Contains(diagonal1);
    bool isDiagonal2Valid = validSequences.Contains(diagonal2);

    return isDiagonal1Valid && isDiagonal2Valid;
}