(function (ng) {
    // Definición del módulo
    var mod = ng.module("eBookModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        // En basePath se encuentran los templates y controladores de módulo
        var basePath = 'src/modules/eBooks/';
        // Mostrar la lista de autores será el estado por defecto del módulo
        $urlRouterProvider.otherwise("/eBooksList");
        // Definición del estado 'eBooksList' donde se listan los autores
        $stateProvider.state('eBooks', {
            url: '/eBooks',
            abstract: true,
            views: {
                'mainView': {
                    templateUrl: basePath + 'eBooks.html',
                    controller: 'eBookCtrl',
                    controllerAs: 'ctrl'
                }
            }
        }).state('eBooksList', {
            // Url que aparecerá en el browser
            url: '/list',
            parent: 'eBooks',
            views: {
                'listView': {
                    templateUrl: basePath + 'eBooks.list.html'
                }
            }
        }).state('eBookDetail', {
            url: '/{eBookId:int}/detail',
            parent: 'eBooks',
            param: {
                eBookId: null
            },
            views: {
                'listView': {
                    templateUrl: basePath + 'eBooks.list.html'
                },
                'detailView': {
                    templateUrl: basePath + 'eBooks.detail.html',
                    controller: 'eBookCtrl',
                    controllerAs: 'ctrl'
                }
            }

        }).state('eBooksCreate', {
            url: '/create',
            parent: 'eBooks',
            views: {
                'detailView': {
                    templateUrl: basePath + '/new/eBook.new.html',
                    controller: 'eBookNewCtrl'
                }
            },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
        }).state('eBookUpdate', {
            url: '/update/{eBookId:int}',
            parent: 'eBooks',
            param: {
                eBookId: null
            },
            views: {
                'detailView': {
                    templateUrl: basePath + '/new/eBook.new.html',
                    controller: 'eBookUpdateCtrl'
                }
            },
            data: {
                requireLogin: true,
                roles: ['admin', 'assistant']
            }
        }).state('eBookDelete', {
            url: '/delete/{eBookId:int}',
            parent: 'eBooks',
            param: {
                eBookId: null
            },
            views: {
                'detailView': {
                    templateUrl: basePath + '/delete/eBook.delete.html',
                    controller: 'eBookDeleteCtrl'
                }
            },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
        });
    }
    ]);
})(window.angular);