(function (ng) {
    // Definición del módulo
    var mod = ng.module("videoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/videos/';
            // Mostrar la lista de videos será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/videosList");
            // Definición del estado 'videosList' donde se listan los videos
            $stateProvider.state('videosList', {
                // Url que aparecerá en el browser
                url: '/videos/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'videos.list.html',
                        controller: 'videoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);