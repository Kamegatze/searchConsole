1) Для чтения файла использовался класс BufferRead;
2) Сравнение строк происходило методом regionMatches;
3) Что бы постоянно построчно не перечитывать файл использоался созданный объект Cache, который перед поиском загружает часть файла;
