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
        $stateProvider.state('eVideos', {
            url: '/eVideos',
            abstract: true,
            views: {
                'mainView': {
                    templateUrl: basePath + 'eVideos.html',
                    controller: 'eVideoCtrl',
                    controllerAs: 'ctrl'
                }
            }
        }).state('eVideosList', {
            // Url que aparecerá en el browser
            url: '/list',
            parent: 'eVideos',
            views: {
                'listView': {
                    templateUrl: basePath + 'eVideos.list.html'
                }
            }
        }).state('eVideoDetail', {
            url: '/{eVideoId:int}/detail',
            parent: 'eVideos',
            param: {
                eVideoId: null
            },
            views: {
                'listView': {
                    templateUrl: basePath + 'eVideos.list.html'
                },
                'detailView': {
                    templateUrl: basePath + 'eVideos.detail.html',
                    controller: 'eVideoCtrl',
                    controllerAs: 'ctrl'
                }
            }

        }).state('eVideosCreate', {
            url: '/create',
            parent: 'eVideos',
            views: {
                'detailView': {
                    templateUrl: basePath + '/new/eVideo.new.html',
                    controller: 'eVideoNewCtrl'
                }
            }
        }).state('eVideoUpdate', {
            url: '/update/{eVideoId:int}',
            parent: 'eVideos',
            param: {
                eVideoId: null
            },
            views: {
                'detailView': {
                    templateUrl: basePath + '/new/eVideo.new.html',
                    controller: 'eVideoUpdateCtrl'
                }
            }
        }).state('eVideoDelete', {
            url: '/delete/{eVideoId:int}',
            parent: 'eVideos',
            param: {
                eVideoId: null
            },
            views: {
                'detailView': {
                    templateUrl: basePath + '/delete/eVideo.delete.html',
                    controller: 'eVideoDeleteCtrl'
                }
            }
        });
    }
    ]);
})(window.angular);