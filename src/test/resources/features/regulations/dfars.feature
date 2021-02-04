@all @regulations @dfars-regulation
Feature: Regulations

  @validate-dfars-layout
  Scenario: DFARS - Page Layout
    Given I am on the DFARS regulation page
    Then the page title is "DFARS"
    And the page breadcrumbs are the following:
      | Home | Regulations | DFARS |
    And I see the following sidebar links:
      | DFARS PGI | DFARSPGI regulation |
      | Index     | DFARS regulation    |
    And I see the following sidebar parts:
      | 201 | 202 | 203 | 204 | 205 | 206 | 207 | 208 |
      | 209 | 210 | 211 | 212 | 213 | 214 | 215 | 216 |
      | 217 | 218 | 219 | 220 | 221 | 222 | 223 | 224 |
      | 225 | 226 | 227 | 228 | 229 | 230 | 231 | 232 |
      | 233 | 234 | 235 | 236 | 237 | 238 | 239 | 240 |
      | 241 | 242 | 243 | 244 | 245 | 246 | 247 | 248 |
      | 249 | 250 | 251 | 252 | 253 |     |     |     |
      | A   | B   | C   | D   | E   | F   | G   | H   |
      | I   |     |     |     |     |     |     |     |
    And I see the following full download table headers:
      | Change Number | Effective Date | DFARS Archive | HTML | DITA | PDF | Word |
    And each part and sub-part goes to the correct page
    And I see the following ways to get the part downloads:
      | HTML | DITA | Print |
    And each sidebar link goes to the correct page

  @validate-dfars-pgi-layout
  Scenario: DFARS PGI - Page Layout
    Given I am on the DFARSPGI regulation page
    Then the page title is "DFARS PGI"
    And the page breadcrumbs are the following:
      | Home | Regulations | DFARS PGI |
    And I see the following sidebar links:
      | DFARS Parts | DFARS regulation    |
      | Index       | DFARSPGI regulation |
    And I see the following sidebar parts:
      | 201 | 202 | 203 | 204 | 205 | 206 | 207 | 208 |
      | 209 | 210 | 211 | 212 | 213 | 215 | 216 | 217 |
      | 218 | 219 | 222 | 223 | 225 | 226 | 228 | 229 |
      | 230 | 231 | 232 | 233 | 234 | 235 | 236 | 237 |
      | 239 | 241 | 242 | 243 | 244 | 245 | 246 | 247 |
      | 249 | 250 | 251 | 252 | 253 |     |     |     |
    And I see the following full download table headers:
      | Change Number | Effective Date | DFARS PGI Archive | HTML | DITA | PDF | Word |
    And each part and sub-part goes to the correct page
    And I see the following ways to get the part downloads:
      | HTML | DITA | Print |
    And each sidebar link goes to the correct page