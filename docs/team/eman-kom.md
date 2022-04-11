---
layout: page
title: Emmanuel Mok's Project Portfolio Page
---

### Project: HRConnect

This product is for tech HR recruiters who prefer to use CLI to store contacts of job applicants and their respective interviews as well as keep track of the status of each applicant through the application process.


Given below are my contributions to the project.

* **New Feature:** Added more flexibility to the `find` command.
  * What it does: Allows users to search of any data in their respective sections (Task/Interview/Applicant)
  using the flags as defined in the app. It also simulates 'AND' and 'OR' statements through the use of
  the `g/` flag.
  * Justification: The previous `find` command was too simplistic in the sense that it can only search for names. With 
  more data fields being added to HRConnect, a stronger search function is required to enable users to effectively
  look for data that they are searching for. Furthermore, the simulation of 'AND' and 'OR' commands gives users more 
  flexibility and control over their search criteria, leading to a better experience for the user.
  * Highlights: This enhancement improves upon the original `find` command. Hence, it requires some analysis into other 
  design considerations to ensure that the enhancement is both intuitive and useful to the user. It was also challenging to 
  implement a custom `g/` flag for the `find` command as it requires custom validity checks which builds upon the 
  ArgumentTokenizer and ArgumentMultiMap.

* **New Feature:** Added an `import` feature to allow job applicants data to be transferred into the address book.
  * What it does: Allows users to import all job applicants data from a csv save file that was previously
  exported out by the `export` command.
  * Justification: This feature is complementary to the `export` command it allows previously exported data to be
  reintegrated back into the address book.
  * Highlights: This enhancement was challenging because the csv save file can be subjected to a lot of other changes by 
  the user. A lot of checks are needed to ensure that the contents of the csv save file contents adhere to the flag 
  formats as defined in the HRConnect app. Furthermore, there is also a check for any duplicates for both within the csv save 
  file and the app itself before adding all the job applicants to the app.
  

* **Code Contributed:** 
  * [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=eman-kom&breakdown=true)


* **Project Management:**
  * Released v1.3 on GitHub ([v1.3 release link](https://github.com/AY2122S2-CS2103T-W11-2/tp/releases/tag/v1.3))
  * Enabled Assertions (Pull Request: [#45](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/45/commits/a0eda15e3016f5416a577dd3256d984753862229))
  * Created the GitHub organization repo


* **Enhancement to existing features:**
  * Updated the help page in the address book (Pull Request: [#78](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/78/files))
  * Changed the application name of the address book (Pull Request: [#94](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/94/files))
  

* **Documentation:**
  * User Guide:
    * Drafted the initial version of the UG (Pull request: [#53](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/53))
    * Drafted the Flags and Parameters Format Constraints & Section Types (Pull request: [#168](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/168/files))
    * Wrote the Find Feature (Task, Interview & Applicant) in the UG
    * Wrote the Import Feature in the UG

  * Developer Guide:
    * Added implementation details of the `find` feature
    * Added implementation details of the `export` feature
    * Added implementation details of the `import` feature


* **Team:**
  * Fixed some merge conflicts
  * Helped to find and fix some bugs
  * Reviewed some PRs (Pull Requests: 
  [#43](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/43), 
  [#50](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/50),
  [#54](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/54),
  [#41](https://github.com/AY2122S2-CS2103T-W11-2/tp/pull/41)) 
  * Assisted team members in resolving any obstacles they face


* **Community:**
  * Submitted 22 bugs for the other team in the PE dry run. ([PED link](https://github.com/eman-kom/ped/issues))
 
