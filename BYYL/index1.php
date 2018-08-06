<?php
    include 'Demo1.php';
    include 'Sentence.php';

    $arr = '';
    $i = 0;

    $fileName = 'test.txt';
    $f = fopen($fileName,'r');
    if ($f == NULL)
    {
        echo '未检测到文件';
        exit();
    }
    while(!feof($f)){
        $arr[$i] = fgetc($f);
        $i++;
    }

    $word = new Word($arr);
    $word->run();
    //识别结果
    $result = $word->getResult();
    //种别编码
    $resultNum = $word->getResultType();
    //类型
    $resultType = $word->getResultNumber();

    echo "当前默认规则为:<br>关键字1  运算符2  界符3  标识符4  常数5<br>对当前词法分析结果为:<br>";
    for ($i = 0 ; $i < count($result) ; $i++ ){
        echo "<br>( ' " .$result[$i].'\'  ,  '. $resultType[$i] . '  ,  '.$resultNum[$i]. " )";
    }
    $sentence = new Sentence($result,$resultType,$resultNum);
    $token = $sentence->run();
    echo "<br><br>"."对当前语句分析结果为:".$token;
?>