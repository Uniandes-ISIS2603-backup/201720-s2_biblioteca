(function (ng) {
    // Definición del módulo
    var mod = ng.module("eVideoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        // En basePath se encuentran los templates y controladores de módulo
        var basePath = 'src/modules/eVideos/';
        // Mostrar la lista de autores será el estado por defecto del módulo
        $urlRouterProvider.otherwise("/eVideosList");
        // Definición del estado 'eVideosList' donde se listan los autores
        $stateProvider.state('eVideosList', {
            // Url que aparecerá en el browser
            url: '/eVideos/list',
            views: {
                'mainView': {
                    templateUrl: basePath + 'eVideos.list.html',
                    controller: 'eVideoCtrl',
                    controllerAs: 'ctrl'
                }
            }
        });
    }
    ]);
})(window.angular);