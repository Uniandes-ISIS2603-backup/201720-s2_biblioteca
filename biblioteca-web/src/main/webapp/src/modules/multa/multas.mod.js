(function (ng) {
    // Definición del módulo
    var mod = ng.module("multaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/multa/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('multas', {
                url: '/multas',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'multas.html',                        
                    }
                }
            }).state('multaList', {
                url: '/list',
                parent: 'multas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'multas.list.html',
                        controller: 'multaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('multaDetail', {
                url: '/{multaId:int}/detail',
                parent: 'multas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'multas.detail.html',
                        controller: 'multaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);