/* Configuração das Máscaras dos Forms */

jQuery(function($){
    $("#inputRg").mask("9999999-9");
    $("#inputCep").mask("99999-999");    
    $("#inputCpf").mask("999.999.999-99");
    $("#inputTelefone1").mask("(99)9999-9999",{placeholder:" "});
    $("#inputTelefone2").mask("(99)9999-9999",{placeholder:" "});
    $("#inputTelefone3").mask("(99)9999-9999",{placeholder:" "});
    $("#inputTelefoneContato").mask("(99)9999-9999",{placeholder:" "});
    $("#inputDtNascimento").mask("99/99/9999",{placeholder:" "});
});