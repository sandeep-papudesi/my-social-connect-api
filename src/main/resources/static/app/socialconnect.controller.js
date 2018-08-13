/**
 * Created by Sandeep Papudesi
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('SocialConnectService', SocialConnectService);

    SocialConnectService.$inject = ['$http'];

    function SocialConnectService($http) {
        var vm = this;

        vm.maxConsData = [];vm.minConsData = [];
        vm.conData = [];vm.retainAllConsData = [];
        
        vm.getMaxConnections = getMaxConnections;
        vm.getMinConnections = getMinConnections;
        vm.getConnection = getConnection;
        vm.retainAllCons  =retainAllCons;
        vm.findPath  =findPath;
        
        init();

        function init(){
        
        }
        
       /*
        * Function to retrieve users with highest connections 
        */
       function getMaxConnections(){
            var url = "/socialconnect/max";
            var socialConnectPromise = $http.get(url);
            socialConnectPromise.then(function(response){
                vm.maxConsData = response.data;
            });
        }
       
       /*
        * Function to retrieve users with lowest direct connections 
        */
       function getMinConnections(){
           var url = "/socialconnect/min";
           var socialConnectPromise = $http.get(url);
           socialConnectPromise.then(function(response){
               vm.minConsData = response.data;
           });
       }
       
       /*
        * Function to retrieve total direct connections based on UserId
        */
        function getConnection(id){
            var url = "/socialconnect/" + id;
            var socialConnectPromise = $http.get(url);
            socialConnectPromise.then(function(response){
                vm.conData = response.data;
            });
        }
        
        /*
         * Function to retrieve total direct connections between users
         */
        function retainAllCons(fromId,toId){
        	console.log("fromID:"+fromId+"  toId:"+toId);
            var url = "/socialconnect/"+fromId+"/"+toId;
            var socialConnectPromise = $http.get(url);
            socialConnectPromise.then(function(response){
                vm.retainAllConsData = response.data;
            });
        }
        
        /*
         * Function to find path between users
         */
        function findPath(sourceId,destinationId){
        	console.log("sourceId:"+sourceId+"  destinationId:"+destinationId);
            var url = "/socialconnect/findpath/"+sourceId+"/"+destinationId;
            var socialConnectPromise = $http.get(url);
            socialConnectPromise.then(function(response){
                vm.findPathData = response.data;
            });
        }
       
    }
})();
