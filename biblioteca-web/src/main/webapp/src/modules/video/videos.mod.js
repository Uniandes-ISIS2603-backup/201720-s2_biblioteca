(function (ng) {
    // Definición del módulo
    var mod = ng.module("videosModule", ['ui.router']);
 mod.constant("videosContext", "api/999/videos");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/video/';
            // Mostrar la lista de videos será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/videosList");
            // Definición del estado 'videosList' donde se listan los videos
            $stateProvider.state('videos', {
                // Url que aparecerá en el browser
                url: '/videos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'videos.html',
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
             }).state('videosList', {
                url: '/list',
                parent: 'videos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'videos.list.html'
                    }
                }
            }).state('videoDetail', {
                url: '/{videoId:int}/detail',
                parent: 'videos',
                param: {
                    videoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'videos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'videos.detail.html',
                        controller: 'videosCtrl',
                        controllerAs: 'ctrl'
                    }

                }
            }).state('videosCreate', {
                url: '/create',
                parent: 'videos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/videos.new.html',
                        controller: 'videoNewCtrl'
                    }
                }
            }).state('videoDelete', {
                url: '/{videoId:int}/delete',
                parent: 'videos',
                param: {
                    videoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/video.delete.html',
                        controller: 'videosDeleteCtrl'
                    }
                }
            }).state('videoUpdate', {
                url: '/{videoId:int}/update',
                parent: 'videos',
                param: {
                    videoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/videos.new.html',
                        controller: 'videosUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);
