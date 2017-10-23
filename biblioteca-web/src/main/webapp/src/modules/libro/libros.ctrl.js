(function (ng) {
    var mod = ng.module("librosModule");
    mod.constant("librosContext", "api/libros");
    mod.controller('librosCtrl', ['$scope', '$http', 'librosContext',
        function ($scope, $http, librosContext) {
            $http.get('data/libro.json').then(function (response) {
                $scope.libroRecords = response.data;
            });
        }
    ]);
}
)(angular);