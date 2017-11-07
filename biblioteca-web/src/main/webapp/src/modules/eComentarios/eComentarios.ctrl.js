(function (ng) {
        var mod = ng.module("eComentarioModule");
        mod.constant("eComentariosContext", "eComentarios");
        mod.constant("eBooksContext", "api/eBooks");
        mod.controller('eComentariosCtrl', ['$scope', '$http', 'eBooksContext', '$state', 'eComentariosContext',
            function ($scope, $http, eBooksContext, $state, eComentariosContext) {
                $http.get('data/eBooks.json').then(function (response) {
                    $scope.eComentariosRecords = response.data;
                });
            }
        ]);
    }
)(angular);