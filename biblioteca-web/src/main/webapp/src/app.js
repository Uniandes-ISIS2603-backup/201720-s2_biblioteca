(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies
        'eBookModule',
        'eVideoModule',
        'usuarioModule',
        'bibliotecaModule',
        'librosModule',
        'prestamoModule',
         'reservaModule',
        'multaModule',
        'medioPagoModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
