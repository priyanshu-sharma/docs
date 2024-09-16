### Git Assignment

1. How do you initialize a new Git repository named "project1" on your local machine?


```
mkdir project1
cd project1/
git init
```

2. How do you add a new file named "index.html" to the "project1" repository?

```
touch index.html
```

3. How do you stage the changes made to "index.html" for committing?

```
git add index.html
```

4. How do you commit the changes made to "index.html" with a commit message of "Add index.html"?

```
git commit -m "added index.html"
```

5. How do you view the commit history of the "project1" repository?

```
git log
```

6. How do you create a new branch named "feature-branch" in the "project1" repository?

```
git checkout -b "feature-branch"
```

7. How do you switch to the "feature-branch" in the "project1" repository?

```
git checkout -b "feature-branch"
```

8. How do you make changes to "index.html" in the "feature-branch"?

```
By editing the file
```

9. How do you stage and commit the changes made in the "feature-branch" with a commit message of "Update index.html in feature branch"?

```
git add index.html
git commit -m "Update index.html in feature branch"
```


10. How do you switch back to the main branch in the "project1" repository?

```
git checkout master
```

11. How do you merge the changes from the "feature-branch" into the main branch?


```
By merging the raised PR - https://github.com/priyanshu-sharma/project1/pull/1
```

12. How do you resolve any merge conflicts that occur during the merge process?

```
git merge feature-branch
```

13. How do you view the changes introduced by the merge commit?

```
git diff
```

14. How do you create a new tag named "v1.0" for the current commit in the "project1" repository?

```
git tag -a v1.0 df2ad5ba3ad71f2d99c35210981dc13000ecde13
```


15. How do you push the "project1" repository to a remote repository named "origin"?

```
git remote add origin https://github.com/priyanshu-sharma/project1.git
```

16. How do you clone the "project1" repository from a remote repository to another machine?

```
git clone https://github.com/priyanshu-sharma/project1.git
```

17. How do you fetch the latest changes from the remote repository to your local "project1" repository?

```
git pull origin master
```

18. How do you pull the latest changes from the remote repository into your current branch in the "project1" repository?

```
git checkout feature-branch
git pull origin feature-branch
```

19. How do you create a new branch named "bug-fix" in the "project1" repository based on a   commit?

```
git checkout -b "bug-fix"
git cherry-pick 3c7876cd996808a21a98dd7735b0d017d5d887ea
git add index.html
git cherry-pick --continue
git push origin bug-fix
```

20. How do you revert the last commit made in the "project1" repository?

```
git revert HEAD
git push origin bug-fixes
```