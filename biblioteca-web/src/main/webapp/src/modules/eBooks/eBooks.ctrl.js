(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/eBooks");
        mod.controller('eBookCtrl', ['$scope', '$http', 'eBooksContext',
            function ($scope, $http, eBooksContext) {
                $http.get('data/eBooks.json').then(function (response) {
                    $scope.eBooksRecords = response.data;
                });
            }
        ]);
    }
)(window.angular);