(function (ng) {
        var mod = ng.module("eComentarioModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eComentariosCtrl', ['$scope', '$http', 'eBooksContext', '$state',
            function ($scope, $http, eBooksContext, $state) {
                $http.get(eBooksContext + '/' + $state.params.eBookId + '/comments').then(function (response) {
                    $scope.comments = response.data;
                    $scope.ebookId = $state.params.eBookId;
                });
            }]);


    }
)(angular);