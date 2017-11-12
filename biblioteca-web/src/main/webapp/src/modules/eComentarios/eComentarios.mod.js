(function (ng) {
    var mod = ng.module("eComentarioModule", ['eBookModule', 'ui.router']);
    mod.constant("eComentariosContext", "eComentarios");
    mod.constant("eBooksContext", "api/999/eBooks");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/eComentarios/';
        $urlRouterProvider.otherwise("/eComentariosList");

        $stateProvider.state('eComentarios', {
            url: '/eComentarios',
            abstract: true,
            parent: 'eBookDetail',
            views: {
                childrenView: {
                    templateUrl: basePath + 'eComentarios.html'
                }
            }
        }).state('eComentariosList', {
            url: '/list',
            parent: 'eComentarios',
            views: {
                'listView': {
                    templateUrl: basePath + 'eComentarios.list.html',
                    controller: 'eComentariosCtrl',
                    controllerAs: 'ctrl'
                }
            }
        });
    }]);
})(window.angular);