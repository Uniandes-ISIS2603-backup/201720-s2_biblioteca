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
        'multaModule',
        'medioPagoModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
