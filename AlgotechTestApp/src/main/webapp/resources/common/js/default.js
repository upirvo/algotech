function validatePesel(pesel){
    var  wagi = [ 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 ];
    var suma = 0;
    for (var i = 0; i < 10; i++)
        suma += parseInt(pesel.substring(i, i + 1)) * wagi[i];
    var  cyfraKontrolna = parseInt(pesel.substring(10, 11));
    suma %= 10;
    suma = 10 - suma;
    suma %= 10;
    return (suma == cyfraKontrolna);
}

PrimeFaces.validator['pesel'] = {
    throwError: function(detail) {
        throw {
            summary: 'Pesel',
            detail: detail
        }
    },
    validate:function(element, value){
        if(!validatePesel(value))
            this.throwError('Niepoprawny pesel');
    }
};

redirect = function (path) {
    location.replace(window.location.protocol + '//' +window.location.host+path);
}

var $ = jQuery.noConflict();