(function (ng) {
    var mod = ng.module("librosModule");
    mod.controller('librosDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idLibro= $state.params.libroId;
            $scope.deleteLibro = function () {
                 $http.delete('api/999/libros' + '/' + idLibro, {}).then(function (response) {
                    $state.go('librosList', {libroId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);