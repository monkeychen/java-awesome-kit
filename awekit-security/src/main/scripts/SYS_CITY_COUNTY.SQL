/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : dss

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 06/01/2020 18:20:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_city_county
-- ----------------------------
DROP TABLE IF EXISTS `sys_city_county`;
CREATE TABLE `sys_city_county` (
  `province_id` int(255) DEFAULT NULL,
  `province_name` varchar(255) DEFAULT NULL,
  `city_id` int(255) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `county_id` int(255) NOT NULL,
  `county_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`county_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_city_county
-- ----------------------------
BEGIN;
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 90, '永泰县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 91, '台江区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 92, '平潭县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 93, '闽清县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 94, '闽侯县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 95, '马尾区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 96, '罗源县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 97, '连江县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 98, '晋安区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 99, '鼓楼区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 100, '福清市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 101, '长乐市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 591, '福州', 102, '仓山区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 104, '翔安区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 105, '同安区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 106, '思明区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 107, '集美区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 108, '湖里区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 592, '厦门', 109, '海沧区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 111, '周宁县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 112, '柘荣县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 113, '霞浦县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 114, '寿宁县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 115, '屏南县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 116, '蕉城区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 117, '古田县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 118, '福鼎市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 593, '宁德', 119, '福安市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 594, '莆田', 121, '秀屿区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 594, '莆田', 122, '仙游县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 594, '莆田', 123, '荔城区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 594, '莆田', 124, '涵江区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 594, '莆田', 125, '城厢区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 127, '永春县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 128, '石狮市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 129, '泉港区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 130, '清蒙开发区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 131, '南安市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 132, '洛江区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 133, '鲤城区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 134, '晋江市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 135, '惠安县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 136, '丰泽区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 137, '德化县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 595, '泉州', 138, '安溪县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 140, '诏安县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 141, '漳浦县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 142, '云霄县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 143, '芗城区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 144, '平和县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 145, '南靖县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 146, '龙文区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 147, '龙海市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 148, '华安县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 149, '东山县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 596, '漳州', 150, '长泰县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 152, '漳平市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 154, '新罗区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 155, '武平县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 156, '上杭县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 157, '连城县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 158, '长汀县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 160, '尤溪县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 161, '永安市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 162, '泰宁县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 163, '沙县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 164, '三元区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 165, '清流县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 166, '宁化县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 167, '明溪县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 168, '梅列区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 169, '将乐县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 170, '建宁县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 598, '三明', 171, '大田县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 173, '政和县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 174, '延平区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 175, '武夷山市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 176, '松溪县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 177, '顺昌县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 178, '邵武市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 179, '浦城县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 181, '建瓯市');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 182, '光泽县');
INSERT INTO `sys_city_county` VALUES (590, '福建', 597, '龙岩', 193, '永定区');
INSERT INTO `sys_city_county` VALUES (590, '福建', 599, '南平', 194, '建阳区');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
