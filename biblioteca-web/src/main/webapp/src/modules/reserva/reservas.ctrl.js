(function (ng) {
    var mod = ng.module("librosModule");
    mod.constant("librosContext", "api/999/libros");
    mod.controller('librosCtrl', ['$scope', '$http', 'librosContext', '$state',
        function ($scope, $http, librosContext, $state) {
            $http.get(librosContext).then(function (response) {
                $scope.libroRecords = response.data;
            });
            
            if ($state.params.libroId !== undefined && ($state.params.bookId !== null)) {
                $http.get(librosContext + '/' + $state.params.libroId).then(function (response) {
                      $scope.libroRecords = response.data.libros;
                  $scope.currentLibro = response.data;
                });
            }
        }
    ]);
}
)(angular);