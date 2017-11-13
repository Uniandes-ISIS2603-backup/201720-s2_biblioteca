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
        'blogModule',
        'comentarioModule',
        'videosModule',
        'salasModule',
        'prestamoModule',
        'reservaModule',
        'multaModule',
        'medioPagoModule',
        'eComentarioModule',
        'loginModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    
})(window.angular);
