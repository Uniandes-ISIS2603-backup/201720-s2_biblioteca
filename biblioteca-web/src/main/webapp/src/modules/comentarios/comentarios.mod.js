(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentarioModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentario/';
            // Mostrar la lista de comentarios será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/comentarioList");
            // Definición del estado 'comentarioList' donde se listan los autores
            $stateProvider.state('comentarioList', {
                // Url que aparecerá en el browser
                url: '/comentario',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.list.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentarioList', {
                url: '/list',
                parent: 'comentarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    }
                }
            }).state('comentarioDetail', {
                url: '/{comentarioId:int}/detail',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'comentarios.detail.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);