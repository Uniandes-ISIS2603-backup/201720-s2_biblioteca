(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/999/eBooks");
        mod.controller('eBookDeleteCtrl', ['$scope', '$http', 'eBooksContext', '$state',
            function ($scope, $http, eBooksContext, $state) {
                var idEBook = $state.params.eBookId;
                $scope.deleteEBook = function () {
                    $http.delete(eBooksContext + '/' + idEBook, {}).then(function (response) {
                        $state.go('eBooksList', {eBookId: response.data.id}, {reload: true});
                    });
                };
            }
        ]);
    })(angular);