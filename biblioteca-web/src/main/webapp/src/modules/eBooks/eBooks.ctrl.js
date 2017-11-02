(function (ng) {
        var mod = ng.module("eBookModule");
        mod.constant("eBooksContext", "api/eBooks");
        mod.controller('eBookCtrl', ['$scope', '$http', 'eBooksContext', '$state',
            function ($scope, $http, eBooksContext, $state) {
                $http.get('data/eBooks.json').then(function (response) {
                    $scope.eBooksRecords = response.data;
                });
                if (($state.params.eBookId !== undefined) && ($state.params.eBookId !== null) && ($scope.eBooksRecords)) {
                    // $http.get(eBooksContext + '/' + $state.params.eBookId).then(function (response) {
                    //     $scope.currentEBook = response.data;
                    // });
                    $scope.eBooksRecords.forEach(function(elem){
                        if (elem.id === $state.params.eBookId)
                            $scope.currentEBook = elem;
                    });
                }
            }
        ]);
    }
)(window.angular);