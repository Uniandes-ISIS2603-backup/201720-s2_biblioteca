(function (ng) {
    // Definición del módulo
    var mod = ng.module("bibliotecaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/biblioteca/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/bibliotecasList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('bibliotecasList', {
                // Url que aparecerá en el browser
                url: '/bibliotecas',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bibliotecas.list.html',
                        controller: 'bibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bibliotecaList', {
                url: '/list',
                parent: 'bibliotecas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bibliotecas.list.html'
                    }
                }
            }).state('bibliotecaDetail', {
                url: '/{bibliotecaId:int}',
                parent: 'bibliotecas',
                param: {
                    bibliotecaId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'bibliotecas.detail.html',
                        controller: 'bibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);